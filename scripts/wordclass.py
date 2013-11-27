#!/bin/env python

import json

words = {3: [], 4: [], 5: []}

for word in open('../res/words_en.txt'):
    word = word.strip().upper()
    if len(word) in (3, 4, 5):
        words[len(word)].append(word)


for length in (3, 4, 5):
    ofh = open('../res/words_%d_en.json' % length, 'wb')
    ofh.write(json.dumps(words[length]))
    ofh.close()

