import matplotlib.pyplot as plt
import numpy as np
from sklearn import datasets, svm

# iris 데이터셋 로드 (꽃잎 길이, 꽃잎 폭만 사용)
iris = datasets.load_iris()
X = iris.data[:, :2]  # sepal length, sepal width
y = iris.target

# gamma 값들을 비교
gammas = [0.01, 1, 10]

# 서브플롯 준비
fig, axes = plt.subplots(1, len(gammas), figsize=(15, 5))

for ax, gamma in zip(axes, gammas):
    clf = svm.SVC(gamma=gamma, C=10)
    clf.fit(X, y)

    # 경계 시각화를 위한 격자 만들기
    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
    xx, yy = np.meshgrid(np.linspace(x_min, x_max, 500),
                         np.linspace(y_min, y_max, 500))

    Z = clf.predict(np.c_[xx.ravel(), yy.ravel()])
    Z = Z.reshape(xx.shape)

    # 결정 경계 표시
    ax.contourf(xx, yy, Z, alpha=0.3)
    ax.scatter(X[:, 0], X[:, 1], c=y, edgecolors='k')
    ax.set_title(f"gamma={gamma}")

plt.show()
