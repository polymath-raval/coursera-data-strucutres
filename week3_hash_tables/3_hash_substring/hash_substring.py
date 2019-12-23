# python3

p = 1000000007
x = 263

def read_input():
    return (input().rstrip(), input().rstrip())

def print_occurrences(output):
    print(' '.join(map(str, output)))

def hash(str):
	h = 0
	for c in str[::-1]:
		h = ((h * x) + ord(c)) % p
	return h

def hash_text(text, l):
	text_lst = [ord(c) for c in text]
	result = [0] * (len(text_lst) - l)
	result[len(text_lst) - l - 1] = hash(text[len(text_lst) - l : len(text_lst)])
	exp_x = x ** l
	for i in range(len(text_lst) - l - 2, -1, -1):
		result[i] = (result[i+1] * x  + text_lst[i] - text_lst[i + l] * exp_x) % p
	return result

def get_occurrences(pattern, text):
    return [
        i 
        for i in range(len(text) - len(pattern) + 1) 
        if text[i:i + len(pattern)] == pattern
    ]

if __name__ == '__main__':
    #print_occurrences(get_occurrences(*read_input()))
    print(hash_text('abcabcdf', 2))
