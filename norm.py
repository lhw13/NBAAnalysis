# -*- coding: utf-8 -*-
import numpy as np
from scipy.stats import norm as N

lines = open('forWinRate.txt', 'r').read().splitlines()

for line in lines:
    a=map(np.float64, line.split(","))
rv1=N(a[0], np.sqrt(a[1]))
print rv1.cdf(0)