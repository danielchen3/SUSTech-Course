import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from perceptron import Perceptron
import time

# random_seed
np.random.seed(int(time.time()))

# 定义两个高斯分布的参数
mean1 = [0, 0]
cov1 = [[1, 0], [0, 1]]  # 协方差矩阵

mean2 = [2, 2]
cov2 = [[1, 0], [0, 1]]

# 存储每次测试的准确率
accuracy_list = []

# 执行 100 次测试
for i in range(100):
    points1 = np.random.multivariate_normal(mean1, cov1, 100)
    points2 = np.random.multivariate_normal(mean2, cov2, 100)
    dataset = np.vstack((points1, points2))

    # generate labels (distribution1 refers to -1, distribution2 refers to 1)
    labels = [-1] * 100 + [1] * 100
    data = pd.DataFrame(dataset, columns=["x", "y"])
    data["label"] = labels

    # random
    data = data.sample(frac=1).reset_index(drop=True)

    # 划分训练集（80%）和测试集（20%）
    train_data = data.iloc[:160]
    test_data = data.iloc[160:]

    # train_data
    train_inputs = train_data[["x", "y"]].values
    train_labels = train_data["label"].values

    # test_data
    test_inputs = test_data[["x", "y"]].values
    test_labels = test_data["label"].values

    # initialization every time and do the training process
    perceptron = Perceptron(n_inputs=2, max_epochs=100, learning_rate=0.01)
    perceptron.train(train_inputs, train_labels)

    # test
    accuracy = perceptron.test(test_inputs, test_labels)
    accuracy_list.append(accuracy)

# 计算 100 次测试的平均准确率
average_accuracy = np.mean(accuracy_list)

# 打印最终的测试结果
print(f"Average Accuracy over 100 runs: {average_accuracy * 100:.2f}%")

# 绘制 100 次测试的准确率变化图
plt.figure(figsize=(10, 6))
plt.plot(
    range(1, 101),
    accuracy_list,
    marker="o",
    linestyle="-",
    color="b",
    label="Accuracy per run",
)

# 在图中添加平均准确率的水平线
plt.axhline(
    y=average_accuracy,
    color="r",
    linestyle="--",
    label=f"Average Accuracy: {average_accuracy * 100:.2f}%",
)

# 图表标题及其他元素设置
plt.title("Perceptron Accuracy over 100 runs (Randomized Data)")
plt.xlabel("Run number")
plt.ylabel("Accuracy")
plt.ylim(0, 1)  # 设置 y 轴范围为 0 到 1
plt.grid(True)
plt.legend()
plt.show()
