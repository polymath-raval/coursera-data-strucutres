# python3

from collections import namedtuple

AssignedJob = namedtuple("AssignedJob", ["worker", "started_at"])
Processor = namedtuple("Processor", ["processor_id","next_available"])

parent = lambda i: int(i / 2)
left = lambda i: (2 * i) + 1
right = lambda i: (2 * i) + 2


def swap(data, i, j):
    swap = data[i]
    data[i] = data[j]
    data[j] = swap


def valid_edge(data, parent_idx, child_idx):
    if parent_idx >= len(data) or child_idx >= len(data):
        return True
    elif data[parent_idx].next_available < data[child_idx].next_available:
        return True
    elif data[parent_idx].next_available == data[child_idx].next_available and data[parent_idx].processor_id < data[child_idx].processor_id:
        return True
    return False


def siftup(data, i):
    if i != 0 and not valid_edge(data, parent(i), i):
        swap(data, parent(i), i)
        siftup(data, parent(i))


def fixMinHeap(data, i):
    minidx = i
    if not valid_edge(data, minidx, left(i)):
        minidx = left(i)
    if not valid_edge(data, minidx, right(i)):
        minidx = right(i)
    swap(data, i, minidx)
    return minidx


def siftdown(data, i):
    if not (valid_edge(data, i, left(i)) and valid_edge(data, i, right(i))):
        siftdown(data, fixMinHeap(data, i))


def extractMin(data):
    result = data[0]
    data[0] = data.pop()
    siftdown(data, 0)
    return result


def insert(data, processor):
    data.append(processor)
    siftup(data, len(data) - 1)


def assign_jobs_optimised(n_processor, jobs):
    result = []
    processor_heap = []
    for p in range(n_processor):
        insert(processor_heap, Processor(p, 0))

    for job in jobs:
        processor = extractMin(processor_heap)
        result.append(AssignedJob(processor.processor_id, processor.next_available))
        insert(processor_heap, Processor(processor.processor_id, processor.next_available + job))

    return result


def assign_jobs(n_workers, jobs):
    # TODO: replace this code with a faster algorithm.
    result = []
    next_free_time = [0] * n_workers
    for job in jobs:
        next_worker = min(range(n_workers), key=lambda w: next_free_time[w])
        result.append(AssignedJob(next_worker, next_free_time[next_worker]))
        next_free_time[next_worker] += job

    return result


def main():
    n_workers, n_jobs = map(int, input().split())
    jobs = list(map(int, input().split()))
    assert len(jobs) == n_jobs

    assigned_jobs = assign_jobs_optimised(n_workers, jobs)

    for job in assigned_jobs:
        print(job.worker, job.started_at)


if __name__ == "__main__":
    main()
