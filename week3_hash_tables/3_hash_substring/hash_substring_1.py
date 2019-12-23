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


def hash_code_single_string_optimized(_h, _exp, i, s, l, x, p):
    hash_code = (
        ((_h * x) % p)
        + (ord(s[i : i + 1]) % p)
        - (ord(s[i + l : i + l + 1]) * _exp % p ) 
    ) % p
    return hash_code


def get_occurrences(pattern, text):
    x = X
    prime = PRIME    
    result = []
    plen = len(pattern)
    tlen = len(text)
    exponent_x = (x ** plen)
    pattern_hash = hash_code_single_string(pattern, x, prime)
    last_text_hash = hash_code_single_string(text[tlen - plen : tlen], 
                                       x, prime)
    if last_text_hash == pattern_hash and text[tlen - plen : tlen] == pattern:
        result.append(tlen - plen)
    
    for idx in range(tlen - plen - 1, -1, -1):
        current_text_hash = hash_code_single_string_optimized(last_text_hash, 
                                                         exponent_x, 
                                                         idx, text, 
                                                         plen, 
                                                         x, prime)
        if current_text_hash == pattern_hash and text[idx : idx + plen] == pattern:
            result.append(idx)
        last_text_hash = current_text_hash
        
    return result[::-1]


if __name__ == '__main__':
    print_occurrences(get_occurrences(*read_input()))
    #print(hash_text('abcabcdf', 2))
