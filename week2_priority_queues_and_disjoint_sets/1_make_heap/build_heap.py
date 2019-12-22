# python3

parent = lambda i : i / 2
left = lambda i : (2 * i) + 1
right = lambda i : (2 * i) + 2
valid_edge = lambda data, i, child_idx: i >= len(data) or child_idx(i) >= len(data) or data[i] <= data[child_idx(i)]

def swap(data, i, j):
    swap = data[i]
    data[i] = data[j]
    data[j] = swap


def fixMinHeap(swaps, data, i):
    minidx = i
    if left(i) < len(data) and data[left(i)] < data[minidx]:
        minidx = left(i)
    if right(i) < len(data) and data[right(i)] < data[minidx]:
        minidx = right(i)
    swaps.append((i, minidx))
    swap(data, i, minidx)
    return minidx

def siftdown(swaps, data, i):
    if not ( valid_edge(data, i, left) and valid_edge(data, i, right) ):
        siftdown(swaps, data, fixMinHeap(swaps, data, i))

def build_heap_optimized(data):
    swaps = []
    for i in range(int(len(data) / 2), -1, -1):
        siftdown(swaps, data, i)
    return swaps

def build_heap_naive(data):
    """Build a heap from ``data`` inplace.

    Returns a sequence of swaps performed by the algorithm.
    """
    # The following naive implementation just sorts the given sequence
    # using selection sort algorithm and saves the resulting sequence
    # of swaps. This turns the given array into a heap, but in the worst
    # case gives a quadratic number of swaps.
    #
    # TODO: replace by a more efficient implementation
    swaps = []
    for i in range(len(data)):
        for j in range(i + 1, len(data)):
            if data[i] > data[j]:
                swaps.append((i, j))
                data[i], data[j] = data[j], data[i]
    return swaps


def main():
    n = int(input())
    data = list(map(int, input().split()))
    assert len(data) == n

    swaps = build_heap_optimized(data)

    print(len(swaps))
    for i, j in swaps:
        print(i, j)


if __name__ == "__main__":
    main()
