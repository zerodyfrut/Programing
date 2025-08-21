def convert_c_to_f(celcius_value):
    return celcius_value*9.0/5+32

# 다른문서에서 모듈로 실행되는 것이 아니라
# 현재문서가 main으로 실행될 때만 수행

if __name__=='__main__':
    print(convert_c_to_f(20))
    