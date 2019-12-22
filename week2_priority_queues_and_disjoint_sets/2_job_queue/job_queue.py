# python3

from collections import namedtuple
import heapq 

def assign_jobs_optimised(n_processor, jobs):
    result = []
    heap = []
    for p in range(n_processor):
        heapq.heappush(heap , (p , p, 0))

    for job in jobs:
        (priority_val, processor_id, start_time) = heapq.heappop(heap)
        result.append((processor_id, start_time))
        heapq.heappush(heap , ((start_time+job) * (10 ** 10) + processor_id , processor_id, start_time+job))

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
        print(job[0], job[1])


if __name__ == "__main__":
    main()
