# python3

class Query:

    def __init__(self, query):
        self.type = query[0]
        if self.type == 'check':
            self.ind = int(query[1])
        else:
            self.s = query[1]


class QueryProcessor:
    _multiplier = 263
    _prime = 1000000007

    def __init__(self, bucket_count):
        self.bucket_count = bucket_count
        self.buckets = []
        for idx in range(bucket_count):
            self.buckets.append([])

    def _hash_func(self, s):
        ans = 0
        for c in reversed(s):
            ans = (ans * self._multiplier + ord(c)) % self._prime
        return ans % self.bucket_count

    def read_query(self):
        return Query(input().split())

    def process_query(self, query):
        if query.type == 'add':
            hash_code = self._hash_func(query.s)
            if not query.s in self.buckets[hash_code]:
                self.buckets[hash_code].append(query.s)
        elif query.type == 'del':
            hash_code = self._hash_func(query.s)
            if query.s in self.buckets[hash_code]:
                self.buckets[hash_code].remove(query.s)
        elif query.type == 'find':
            hash_code = self._hash_func(query.s)
            print('yes' if query.s in self.buckets[hash_code] else 'no')
        elif query.type == 'check':
            if len(self.buckets[query.ind]) == 0:
                print('')
            else:
                print(' '.join(reversed(self.buckets[query.ind])))

    def process_queries(self):
        n = int(input())
        for i in range(n):
            self.process_query(self.read_query())

if __name__ == '__main__':
    bucket_count = int(input())
    proc = QueryProcessor(bucket_count)
    proc.process_queries()
