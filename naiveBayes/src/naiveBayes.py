import pandas as pd
from sklearn.metrics import accuracy_score, precision_score, recall_score, f1_score
from ucimlrepo import fetch_ucirepo


def load_data(file_path):
    mushroom = fetch_ucirepo(id=73)
    column_names = mushroom.data.features.columns.tolist()
    list.insert(column_names, 0, 'class')
    data = pd.read_csv(file_path, header=None, names=column_names)
    return data


train_data = load_data('agaricus-lepiota.data')
test_data = load_data('agaricus-lepiota.test.data')


def preprocess_data(data):
    data = data.replace('?', pd.NA)
    data = data.dropna()

    return data


train_data = preprocess_data(train_data)
test_data = preprocess_data(test_data)


def train_naive_bayes(data):
    total_count = len(data)
    class_counts = data['class'].value_counts()
    priors = class_counts / total_count

    likelihoods = {}
    for column in data.columns[1:]:
        likelihoods[column] = {}
        for value in data[column].unique():
            likelihoods[column][value] = {}
            for class_label in class_counts.index:
                likelihoods[column][value][class_label] = (
                            (data[(data[column] == value) & (data['class'] == class_label)].shape[0] + 1) /
                            (class_counts[class_label] + len(data[column].unique())))

    return priors, likelihoods


priors, likelihoods = train_naive_bayes(train_data)


def classify(instance, priors, likelihoods):
    class_probs = {}
    for class_label in priors.index:
        class_prob = priors[class_label]
        for feature, value in instance.items():
            if feature in likelihoods and value in likelihoods[feature]:
                class_prob *= likelihoods[feature][value].get(class_label,
                                                              1 / (len(train_data) + len(likelihoods[feature])))
            else:
                class_prob *= 1 / (len(train_data) + len(likelihoods[feature]))
        class_probs[class_label] = class_prob

    return max(class_probs, key=class_probs.get)


def evaluate_classifier(test_data, priors, likelihoods):
    y_true = test_data['class']
    y_pred = test_data.drop('class', axis=1).apply(lambda x: classify(x, priors, likelihoods), axis=1)

    accuracy = accuracy_score(y_true, y_pred)
    precision = precision_score(y_true, y_pred, pos_label='p')
    recall = recall_score(y_true, y_pred, pos_label='p')
    f1 = f1_score(y_true, y_pred, pos_label='p')

    print(f'Accuracy: {accuracy:.4f}')
    print(f'Precision: {precision:.4f}')
    print(f'Recall: {recall:.4f}')
    print(f'F-measure: {f1:.4f}')


evaluate_classifier(test_data, priors, likelihoods)