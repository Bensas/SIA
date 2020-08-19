from parse import parse
import functools

class DataPoint:
    def __init__(self):
        self.algorithm = ''
        self.heuristic = ''
        self.cost = 0
        self.expanded = 0
        self.frontier = 0
        self.time = 0
        self.level = ''

lines=open('results').readlines()

datapoints = []
current = DataPoint()
for line in lines:
    if line.startswith('algorithm: '):
        r = parse('algorithm: {algorithm}', line)
        current.algorithm = r['algorithm']
    if line.startswith('heuristic: '):
        r = parse('heuristic: {heuristic}', line)
        current.heuristic = r['heuristic']
    elif line.startswith('cost: '):
        r = parse('cost: {cost:d}', line)
        current.cost = r['cost']
    elif line.startswith('expanded: '):
        r = parse('expanded: {expanded:d}', line)
        current.expanded = r['expanded']
    elif line.startswith('frontier: '):
        r = parse('frontier: {frontier:d}', line)
        current.frontier = r['frontier']
    elif line.startswith('time: '):
        r = parse('time: {time:d}', line)
        current.time = r['time']
    elif line.startswith('level: '):
        r = parse('level: {level}', line)
        current.level = r['level']
        datapoints.append(current)
        current = DataPoint()

bfs = list(filter(lambda x: x.algorithm=='BFS', datapoints))
dfs = list(filter(lambda x: x.algorithm=='DFS', datapoints))
iddfs = list(filter(lambda x: x.algorithm=='IDDFS', datapoints))
astar = list(filter(lambda x: x.algorithm=='ASTAR', datapoints))
greedy = list(filter(lambda x: x.algorithm=='GREEDY', datapoints))
idastar = list(filter(lambda x: x.algorithm=='IDASTAR', datapoints)) 

def avg(fun, l, initializer):
    return functools.reduce(fun, l, initializer)/len(l)

def percentageIncrease(l1, l2, attr):
    total = 0
    for i in range(0, len(l1)):
        total += (getattr(l2[i], attr)-getattr(l1[i], attr))/getattr(l1[i], attr)
    return total*100/len(l1)

def percentageDecrease(l1, l2, attr):
    total = 0
    for i in range(0, len(l1)):
        total += (getattr(l1[i], attr)-getattr(l2[i], attr))/getattr(l1[i], attr)
    return total*100/len(l1)

