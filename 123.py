# -*- coding: utf-8 -*-
#最小二乘法，多变量线性回归
import numpy as np
from scipy import stats


lines = open('datax.txt', 'r').read().splitlines()
linesy = open('datay.txt', 'r').read().splitlines()
m = []
n = []
for line in lines:
    m.append(map(np.float64, line.split(",")))

for line in linesy:
    n.append(map(np.float64, line.split(",")))

x=np.matrix(m)
y=np.matrix(n).T

#print x
#print y
b=(x.T*x).I*x.T*y

f = open('b.txt', 'w')
raw = b.shape
for i in range(0,raw[0]-1):
	f.write(str(b[i,0])+',')
f.write(str(b[raw[0]-1,0]))
temp_e=y-x*b
mye=temp_e.sum()/temp_e.size
f.close()

