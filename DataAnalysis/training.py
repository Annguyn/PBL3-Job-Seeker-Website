import pandas as pd
import re
import matplotlib.pyplot as plt
import re
data = pd.read_csv("analyzed_data.csv")
data.columns

# %%
features =  ['location', 'experience', 'phonenumber', 'Python', 'Java',
       'JavaScript', 'C++', 'C#', 'PHP', 'Ruby', 'Swift', 'TypeScript', 'Go',
       'Kotlin', 'Rust', 'Lua', 'Perl', 'SQL', 'HTML', 'CSS', 'R', 'MATLAB',
       'Shell', 'Assembly', '.NET', 'C',  'Web',
       'Android', 'IOS', 'Backend', 'Frontend', 'Machine Learning', 'Data',
       'Game', 'Embedded', 'Network', 'Computer Science', 'Software',
       'Security', 'Robot', 'Cloud', 'AI', 'Nhúng', 'Bridge', 'Software.1',
       'Designer', 'Scrum', 'BrSE', 'Tester', 'Comtor','level_number','location_number']

X = data[features]
X.head()



y = data['maxSalary']
y.head()

from sklearn.model_selection import train_test_split
print(data.columns)

# %%
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression, LinearRegression
# data_filtered = data.loc[data['maxSalary'] != 0]
data_filtered = data.loc[(data['maxSalary_std'] != 0) &
                     #      &((data['Python'] == 1) | (data['Java'] == 1) | (data['JavaScript'] == 1) | 
                     #      (data['C++'] == 1) | (data['C#'] == 1) | (data['PHP'] == 1) | 
                     #      (data['Ruby'] == 1) | (data['Swift'] == 1) | (data['TypeScript'] == 1) | 
                     #      (data['Go'] == 1) | (data['Kotlin'] == 1) | (data['Rust'] == 1) | 
                     #      (data['Lua'] == 1) | (data['Perl'] == 1) | (data['SQL'] == 1) | 
                     #      (data['HTML'] == 1) | (data['CSS'] == 1) | (data['R'] == 1) | 
                     #      (data['MATLAB'] == 1) | (data['Shell'] == 1) | (data['Assembly'] == 1) | 
                     #      (data['.NET'] == 1) | (data['C'] == 1)) &
                         (data['level_number'] >= 1) &
                          ( data['experience'] >= 1) & (data['experience'] <= 30) & (data['location_number'] != 0 )
                        ]
                         

                     #     & ((data['Web'] == 1) | (data['Android'] == 1) | (data['IOS'] == 1) | 
                     #      (data['Backend'] == 1) | (data['Frontend'] == 1) | (data['Machine Learning'] == 1) | 
                     #      (data['Data'] == 1) | (data['Game'] == 1) | (data['Embedded'] == 1) | 
                     #      (data['Network'] == 1) | (data['Computer Science'] == 1) | (data['Software'] == 1) | 
                     #      (data['Security'] == 1) | (data['Robot'] == 1) | (data['Cloud'] == 1) | 
                     #      (data['AI'] == 1) | (data['Nhúng'] == 1) | (data['Bridge'] == 1) | 
                     #      (data['Software.1'] == 1) | (data['Designer'] == 1) | (data['Scrum'] == 1) | 
                     #      (data['BrSE'] == 1) | (data['Tester'] == 1) | (data['Comtor'] == 1))]

# Check the filtered data
print(data_filtered)

features = [ 'experience', 'Python', 'Java',
       'JavaScript', 'C++', 'C#', 'PHP', 'Ruby', 'Swift', 'TypeScript', 'Go',
       'Kotlin', 'Rust', 'Lua', 'Perl', 'SQL', 'HTML', 'CSS', 'R', 'MATLAB',
       'Shell', 'Assembly', '.NET', 'C', 'Tiếng Anh', 'Tiếng Nhật', 'Tiếng Trung', 'Web', 'Android',
       'IOS', 'Backend', 'Frontend', 'Machine Learning', 'Data', 'Game',
       'Embedded', 'Network', 'Computer Science', 'Software', 'Security',
       'Robot', 'Cloud', 'AI', 'Nhúng', 'Bridge', 'Software.1', 'Designer',
       'Scrum', 'BrSE', 'Tester', 'Comtor' ,'level_number','location_number']

# Assign features and target
X = data_filtered[features]
y = data_filtered['maxSalary_std']

# Chia dữ liệu thành tập huấn luyện và tập kiểm tra
X_train, X_valid, y_train, y_valid = train_test_split(X, y, train_size=0.8, test_size=0.2, random_state=0)
logistic_model = LogisticRegression()
logistic_model.fit(X_train.drop(columns=['experience', 'level_number']), y_train)

# Tạo và huấn luyện mô hình Linear Regression cho thuộc tính 'experience' và 'level_number'
linear_model = LinearRegression()
linear_model.fit(X_train[['experience', 'level_number']], y_train)

# Đánh giá mô hình
train_score_logistic = logistic_model.score(X_train.drop(columns=['experience', 'level_number']), y_train)
valid_score_logistic = logistic_model.score(X_valid.drop(columns=['experience', 'level_number']), y_valid)
train_score_linear = linear_model.score(X_train[['experience', 'level_number']], y_train)
valid_score_linear = linear_model.score(X_valid[['experience', 'level_number']], y_valid)
print("Logistic Regression - Training accuracy:", train_score_logistic)
print("Logistic Regression - Validation accuracy:", valid_score_logistic)
print("Linear Regression - Training R^2 score:", train_score_linear)
print("Linear Regression - Validation R^2 score:", valid_score_linear)



y_pred_linear_train = linear_model.predict(X_train[['experience','level_number']])
y_pred_linear_valid = linear_model.predict(X_valid[['experience','level_number']])

y_pred_logistic_train = logistic_model.predict(X_train.drop(columns=['experience','level_number']))
y_pred_logistic_valid = logistic_model.predict(X_valid.drop(columns=['experience','level_number']))

print("Predictions using Linear Regression:")
print("Training set:", y_pred_linear_train)
print("Validation set:", y_pred_linear_valid)

print("\nPredictions using Logistic Regression:")
print("Training set:", y_pred_logistic_train)
print("Validation set:", y_pred_logistic_valid)

combined_y_pred_train = 0.6 * y_pred_linear_train + 0.4 * y_pred_logistic_train
combined_y_pred_valid = 0.6 * y_pred_linear_valid + 0.4 * y_pred_logistic_valid

# In ra kết quả dự đoán
print("Combined Predictions:")
print("Training set:", combined_y_pred_train)
print("Validation set:", combined_y_pred_valid)

plt.figure(figsize=(10, 5))

plt.scatter(X_valid['experience'], y_valid, color='blue', label='Actual')

plt.plot(X_valid['experience'], y_pred_linear_valid, color='red', linestyle='-', linewidth=2, label='Linear Regression Prediction')

plt.plot(X_valid['experience'], y_pred_logistic_valid, color='green', linestyle='-', linewidth=2, label='Logistic Regression Prediction')

plt.plot(X_valid['experience'], combined_y_pred_valid, color='orange', linestyle='-', linewidth=2, label='Combined Prediction')

plt.xlabel('Experience')
plt.ylabel('Salary')
plt.title('Comparison of Predictions')
plt.legend()
plt.grid(True)

plt.show()


df = pd.DataFrame({'Lương thực tế': y_valid, 'Lương dự đoán': combined_y_pred_valid.round(-6), 'Sai số': y_valid - combined_y_pred_valid.round(-6)})
print(df)
# Hàm Excel 
# =IF(AND(I2=0,J2<>0),H2*10,I2)
# =IF(AND(H2<10000000, I2<10000000), LEFT(H2, 1) & LEFT(I2, 1) * 1000000, IF(AND(I2=0, J2<>0), H2*10, I2))

