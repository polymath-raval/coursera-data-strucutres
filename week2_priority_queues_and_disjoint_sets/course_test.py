def merge(parents, ranks, src, dst):
        src_parent = get_parent(parents, src)
        dst_parent = get_parent(parents, dst)

        if src_parent == dst_parent:
            return False

        if ranks[src_parent] > ranks[dst_parent]:
            parents[dst_parent] = src_parent
        else:
            parents[src_parent] = dst_parent
            if ranks[src_parent] == ranks[dst_parent]:
                ranks[dst_parent] = ranks[dst_parent] + 1

        return True

def merge_1(parents, ranks, src, dst):
        src_parent = get_parent_1(parents, src)
        dst_parent = get_parent_1(parents, dst)

        if src_parent == dst_parent:
            return False

        if ranks[src_parent] > ranks[dst_parent]:
            parents[dst_parent] = src_parent
        else:
            parents[src_parent] = dst_parent
            if ranks[src_parent] == ranks[dst_parent]:
                ranks[dst_parent] = ranks[dst_parent] + 1

        return True

def get_parent(parents, i):
    if parents[i] != i:
        parents[i] = get_parent(parents, parents[i])
    return parents[i]

def get_parent_1(parents, i):
    if parents[i] != i:
        return get_parent(parents, parents[i])
    return parents[i]

def test():
    parents = [0] * 61
    ranks = [0] * 61
    for i in range(1, 61):
      parents[i] = i
      ranks[i] = 0
    for i in range(1, 31):
      merge(parents, ranks, i, 2*i)
    for i in range(1, 21):
      merge(parents, ranks, i, 3*i)
    for i in range(1, 13):
      merge(parents, ranks, i, 5*i)
    for i in range(1, 61):
      get_parent(parents, i)
    set_parents = set(parents)
    set_parents.remove(0)
    for i in set_parents:
        print(ranks[i])

def test1():
    parents = [0] * 13
    ranks = [0] * 13
    for i in range(1, 13):
      parents[i] = i
      ranks[i] = 0
    merge_1(parents, ranks, 2, 10)
    merge_1(parents, ranks, 7, 5)
    merge_1(parents, ranks, 6, 1)
    merge_1(parents, ranks, 3, 4)
    merge_1(parents, ranks, 5, 11)
    merge_1(parents, ranks, 7, 8)
    merge_1(parents, ranks, 7, 3)
    merge_1(parents, ranks, 12, 2)
    merge_1(parents, ranks, 9, 6)
    set_parents = set(parents)
    set_parents.remove(0)
    for i in set_parents:
        print(ranks[i])

def test2(n):
    parents = [0] * n
    ranks = [0] * n
    for i in range(n):
          parents[i] = i
          ranks[i] = 0
    for i in range(n-1):
        merge(parents, ranks, i, i+1)
    set_parents = set(parents)
    print(*ranks)
    print(*parents)
    for i in set_parents:
        print(i, ranks[i])

if __name__ == "__main__":
    test()