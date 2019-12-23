# python3

PRIME = 1000000007
X = 263

def read_input():
    return (input().rstrip(), input().rstrip())


def print_occurrences(output):
    print(' '.join(map(str, output)))


def hash_code_single_string(s, x, p):
    h = 0
    for c in s[::-1]:
        h = ((h * x) % p + ord(c) % p) % p
    return h


def hash_code_permutation_naive(s, l, x, p):
    hash_code = []
    hash_str = []
    for i in range(len(s) - l + 1):
        _s = s[i : i + l]
        hash_code.append(hash_code_single_string(_s, x, p))
        hash_str.append(_s)
    return (hash_str, hash_code)


def hash_code_permutation_optimized(s, l, x, p):
    len_result =  len(s) - l + 1
    hash_code = [0] * (len_result)
    hash_code[len_result - 1] = hash_code_single_string(s[len_result - 1 : len(s)], x, p)
    exponent_x = (x ** l)
    for i in range(len_result - 2, -1, -1):
        hash_code[i] = (
            (hash_code[i + 1] * x) % p
            + ord(s[i : i + 1]) % p
            - (ord(s[i + l : i + l + 1]) * exponent_x % p ) 
        ) % p
    return hash_code


def get_occurrences(pattern, text):
    x = X
    prime = PRIME
    hash_p = hash_code_single_string(pattern, x, prime)
    result = []
    hash_c = hash_code_permutation_optimized(text, len(pattern), x, prime)
    for idx in range(len(hash_c)):
        if hash_c[idx] == hash_p:
            if text[idx : idx + len(pattern)] == pattern:
                result.append(idx)
    return result


if __name__ == '__main__':
    print_occurrences(get_occurrences(*read_input()))
    #print(hash_text('abcabcdf', 2))
