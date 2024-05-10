import random
import numpy as np
from scipy.spatial import distance

class DataPoint:
    def __init__(self, vector, data_class):
        self.vector = vector,
        self.data_class = data_class



class KMeansClustering:

    def __init__(self, n_clusters, data_points):
        self.n_clusters = n_clusters
        self.data_points = data_points
        self.dimension = len(self.data_points[0].vector)
        self.clusters = dict()
        for i in range(self.n_clusters):
            self.clusters[i] = []
        for data_point in data_points:
            self.clusters[random.randint(0, n_clusters-1)].append(data_point)

    def calculate_centroid(self, cluster):
        centroid = []
        for i in range(self.dimension):
            for point in cluster:
                centroid[i] += point.vector[i]
                centroid[i] / len(cluster)
        return centroid


    def clusterize(self):
        while :
            centroids = []
            for cluster in self.clusters.values():
                centroids.append(self.calculate_centroid(cluster))
            for data_point in self.data_points:
                closest = distance.euclidean(centroids[0], data_point.vector)
                index = 0
                for centroid in centroids:
                    cur_distance = distance.euclidean(centroid, data_point.vector)
                    if cur_distance < closest:
                        closest = distance
                        index = centroids.index(centroid)
                self.clusters[index].append(data_point)

def read_from_file(file_name):
    data_points = []
    file = open(file_name, "r")
    for line in file:
        line_split = line.split(',')
        data_points.append(DataPoint(line_split[:-1], line_split[-1]))
    return data_points


KMeansClustering = KMeansClustering(4, read_from_file('iris.data'))

