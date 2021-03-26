import pandas as pd
from sklearn.tree import DecisionTreeRegressor
from sklearn.model_selection import train_test_split
import numpy as np

bohol_weather = pd.read_csv('yearly_monthly_averages.csv')
header = ['year', 'month', 'maxtempC', 'mintempC',
       'avgtempC', 'rainMM', 'rain_days', 'maxwindspeedKmph',
       'avgwindspeedKmph', 'avgwindgustKmph', 'visibilityKm', 'humidity',
       'pressureMB', 'cloudcover', 'sun_hour', 'sun_days','uvindex']
bw = bohol_weather[header]
bw_ave = ((bw + bw.shift(-1)) / 3)[::3]


a = bw.iloc[132:144, 1:]
a = a.groupby(a.index // 3).mean()
for i in range(4):
    a.loc[44+i, 'month'] = 1+i

IP = pd.read_csv('bohol_irigated_palay.csv')
RP = pd.read_csv('bohol_rainfed_palay.csv')
WC = pd.read_csv('bohol_white_corn.csv')
YC = pd.read_csv('bohol_yellow_corn.csv')

IP_x = IP.copy()
IP_y = IP_x.pop('Yield')
IR_reg = decisionTree(IP_x, IP_y)
print('Irigated Palay Prediction')
print(IR_reg.predict(b))
# Predict 2020 Quarter 1 - 4
print('Actual Value')
print('35963.00|20407.70|4293.00|61408.00')

RP_x = RP.copy()
RP_y = RP_x.pop('Yield')
RP_reg = decisionTree(RP_x, RP_y)
print('Rainfed Palay Prediction')
print(RP_reg.predict(b))
# Predict 2020 Quarter 1 - 4
print("Actual Value")
print('13945.00|5890.08|831.00|46095.00')

WC_x = WC.copy()
WC_y = WC_x.pop('Yield')
WC_reg = decisionTree(WC_x, WC_y)
print('White Corn Prediction')
print(WC_reg.predict(b))
# Predict 2020 Quarter 1 - 4
print('Actual Value')
print('1835.00|658.08|6576.00|1950.00')

YC_x = YC.copy().dropna()
YC_y = YC_x.pop('Yield')
YC_reg = decisionTree(YC_x, YC_y)
print('Yellow Corn Prediction')
print(YC_reg.predict(b))
# Predict 2020 Quarter 1 - 4
print('Actual Value')
print('48.00|91.00|50.00|74.00')
