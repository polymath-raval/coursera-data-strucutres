# python3
import timeit
def compute_left(sequence, m):
	left = [0] * len(sequence)
	for idx in range(len(sequence)):
		if idx % m == 0:
			left[idx] = sequence[idx]
		elif left[idx - 1] > sequence[idx]:
			left[idx] = left[idx - 1]
		else:
			left[idx] = sequence[idx]
	return left

def compute_right(sequence, m):
	right = [0] * len(sequence)
	right[len(sequence) - 1] = sequence[len(sequence) - 1]
	for idx in range(len(sequence) - 2, -1, -1):
		if (idx + 1) % m == 0:
			right[idx] = sequence[idx]
		elif right[idx + 1] > sequence[idx]:
			right[idx] = right[idx + 1]
		else:
			right[idx] = sequence[idx]
	return right


def max_sliding_window_dp(sequence, m):
	result = [0] * (len(sequence) - m + 1)
	left = compute_left(sequence, m)
	right = compute_right(sequence, m)
	for j in range(m - 1, len(sequence)):
		i = j - m + 1
		if right[i] > left[j]:
			result[i] = right[i]
		else:
			result[i] = left[j]
	return result

def max_sliding_window_naive(sequence, m):
    maximums = []
    for i in range(len(sequence) - m + 1):
        maximums.append(max(sequence[i:i + m]))

    return maximums

if __name__ == '__main__':
    n = int(input())
    input_sequence = [int(i) for i in input().split()]
    assert len(input_sequence) == n
    window_size = int(input())

    print(*max_sliding_window_dp(input_sequence, window_size))

