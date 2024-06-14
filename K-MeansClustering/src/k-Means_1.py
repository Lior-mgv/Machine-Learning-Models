import random

import numpy as np
from scipy.spatial import distance



class DataPoint:
    vector = []

    def __init__(self, vector, data_class):
        self.vector = vector
        self.data_class = data_class


def read_from_file(file_name):
    data_points = []
    file = open(file_name, "r")
    for line in file:
        line_split = line.split(',')
        data_points.append(DataPoint([float(i) for i in line_split[:-1]], line_split[-1]))
    return data_points


class KMeansClustering:

    def __init__(self, k):
        self.k = k

    def clusterize(self, data_set):
        centroids = {}
        for i in range(self.k):
            centroids[i] = np.array(data_set[random.randint(0, len(data_set) - 1)])

        count = 0
        while True:
            clusters = {}
            for i in range(self.k):
                clusters[i] = []
            for data_point in data_set:
                distances = []
                for centroid in centroids.values():
                    distances.append(distance.euclidean(data_point, centroid))
                clusters[distances.index(np.min(distances))].append(data_point)

            total_distance = 0
            for i in range(self.k):
                total_distance += sum([distance.euclidean(x, centroids[i]) for x in clusters[i]])

            print(f'Iteration {count}: {total_distance}')
            old_centroids = centroids.copy()
            centroids_equal = True
            for i in range(self.k):
                centroids[i] = np.mean(clusters[i], axis=0)
                if np.any(centroids[i] != old_centroids[i]):
                    centroids_equal = False
            count += 1
            if centroids_equal:
                return clusters


if __name__ == "__main__":
    data_points = read_from_file("iris.data")
    k_means = KMeansClustering(3)
    clusters = k_means.clusterize([point.vector for point in data_points])
    print(clusters)
