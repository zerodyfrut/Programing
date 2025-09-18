import pandas as pd
import numpy as np
import datetime

import os
print("현재 작업 디렉터리:", os.getcwd())
print("파일 존재 여부:", os.path.exists('./day0917/data/Online_Retail.xlsx'))

# 1️⃣ 데이터 불러오기
retail_df = pd.read_excel('./day0917/data/Online_Retail.xlsx')

print("원본 데이터 shape:", retail_df.shape)
print("원본 고객 수:", retail_df['CustomerID'].nunique())

# 2️⃣ Quantity > 0 필터링
retail_df_qty = retail_df[retail_df['Quantity'] > 0]
print("Quantity > 0 후 shape:", retail_df_qty.shape)
print("Quantity > 0 후 고객 수:", retail_df_qty['CustomerID'].nunique())

# 3️⃣ UnitPrice > 0 필터링
retail_df_price = retail_df_qty[retail_df_qty['UnitPrice'] > 0]
print("UnitPrice > 0 후 shape:", retail_df_price.shape)
print("UnitPrice > 0 후 고객 수:", retail_df_price['CustomerID'].nunique())

# 4️⃣ CustomerID 결측치 제거
retail_df_customer = retail_df_price[retail_df_price['CustomerID'].notnull()]
print("CustomerID 결측치 제거 후 shape:", retail_df_customer.shape)
print("CustomerID 결측치 제거 후 고객 수:", retail_df_customer['CustomerID'].nunique())

# 5️⃣ CustomerID 정수형 변환
retail_df_customer['CustomerID'] = retail_df_customer['CustomerID'].astype(int)

# 6️⃣ 중복 제거
retail_df_no_dup = retail_df_customer.drop_duplicates()
print("중복 제거 후 shape:", retail_df_no_dup.shape)
print("중복 제거 후 고객 수:", retail_df_no_dup['CustomerID'].nunique())

# 7️⃣ SaleAmount 컬럼 추가
retail_df_no_dup['SaleAmount'] = retail_df_no_dup['UnitPrice'] * retail_df_no_dup['Quantity']

# 8️⃣ 고객별 집계
aggregations = {
    'InvoiceNo': 'count', 
    'SaleAmount': 'sum', 
    'InvoiceDate': 'max'
}
customer_df = retail_df_no_dup.groupby('CustomerID').agg(aggregations).reset_index()
customer_df = customer_df.rename(columns={'InvoiceNo':'Freq', 'InvoiceDate':'LastPurchase'})

print("고객별 집계 후 shape:", customer_df.shape)

# 9️⃣ 마지막 구매 후 경과일 계산
customer_df['ElapsedDays'] = (datetime.datetime(2011,12,10) - customer_df['LastPurchase']).dt.days + 1

print("ElapsedDays 계산 후 shape:", customer_df.shape)

# 10️⃣ 로그 변환 후 NaN 확인
customer_df['Freq_log'] = np.log1p(customer_df['Freq'])
customer_df['SaleAmount_log'] = np.log1p(customer_df['SaleAmount'])
customer_df['ElapsedDays_log'] = np.log1p(customer_df['ElapsedDays'])

print("로그 변환 후 NaN 수 확인:")
print(customer_df[['Freq_log', 'SaleAmount_log', 'ElapsedDays_log']].isnull().sum())

print("최종 고객 수:", customer_df.shape[0])
