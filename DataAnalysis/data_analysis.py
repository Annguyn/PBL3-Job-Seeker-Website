# %%
import re
import pandas as pd
import re
from collections import Counter
import matplotlib.pyplot as plt
import csv
import re
# https://jobscentral.com.sg/jobs

# %%
tuyendung = pd.read_csv("output1.csv")
tuyendung.head()



# %% [markdown]
# # Vẽ biểu đồ ngôn ngữ thông dụng

# %% [markdown]
# # Tạo thuộc tính mới từ text -> Vị trí làm việc, email , ngôn ngữ yêu cầu

# %% [markdown]
# ### Location 

# %%
# def read_locations_file(locations_file):
#     with open(locations_file, 'r', encoding='utf-8') as file:
#         locations = [line.strip() for line in file]
#     return locations

# def add_location_to_csv(input_csv, locations_file, output_csv):
#     locations = read_locations_file(locations_file)
#     output_data = []

#     with open(input_csv, 'r', encoding='utf-8') as csvfile:
#         reader = csv.DictReader(csvfile)
#         for row in reader:
#             content = row['content'].lower()  
#             location = find_location(content, locations)
#             row['location'] = location
#             output_data.append(row)

#     with open(output_csv, 'w', newline='', encoding='utf-8') as csvfile:
#         fieldnames = ['post_id', 'content', 'location', 'images']
#         writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
#         writer.writeheader()
#         for row in output_data:
#             writer.writerow(row)

# def find_location(content, locations):
#     for location in locations:
#         if location.lower() in content:
#             return location
#     return 'Unknown'


# %% [markdown]
# ### Email

# %%
import json
def read_locations_json(locations_json):
    with open(locations_json, 'r', encoding='utf-8') as file:
        locations_data = json.load(file)
    return locations_data

def add_location_to_csv(input_csv, locations_json, output_csv):
    locations_data = read_locations_json(locations_json)
    output_data = []

    with open(input_csv, 'r', encoding='utf-8') as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            content = row['content'].lower()  
            location = find_location(content, locations_data)
            row['location'] = location
            output_data.append(row)

    with open(output_csv, 'w', newline='', encoding='utf-8') as csvfile:
        fieldnames = ['post_id', 'content', 'location', 'images']
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        for row in output_data:
            writer.writerow(row)

def find_location(content, locations_data):
    for province_id, province_info in locations_data.items():
        province_name = province_info['name'].lower()
        if province_name in content:
            return province_name
        for district_id, district_name in province_info['districts'].items():
            if district_name.lower() in content:
                return province_name
    return 'Unknown'



# %%
def extract_email(text):
    pattern = r'\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}\b'
    emails = re.findall(pattern, text)
    return emails[0] if emails else 'Unknown'

def update_analyzed_data_with_email(input_csv_file, output_csv_file):
    with open(input_csv_file, 'r', encoding='utf-8') as input_file:
        reader = csv.DictReader(input_file)
        rows = list(reader)

    for row in rows:
        row['email'] = extract_email(row['content'])

    with open(output_csv_file, 'w', newline='', encoding='utf-8') as output_file:
        fieldnames = ['post_id', 'content', 'images', 'email', 'location']
        writer = csv.DictWriter(output_file, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)

# %% [markdown]
# # Ngôn ngữ lập trình

# %%
def addProgrammingLanguagesToCSV(input_csv, programming_languages_file, output_csv):
    with open(input_csv, 'r', newline='', encoding='utf-8') as input_file:
        reader = csv.DictReader(input_file)
        rows = list(reader)

    with open(programming_languages_file, 'r', encoding='utf-8') as lang_file:
        programming_languages = [line.strip() for line in lang_file]

    fieldnames = reader.fieldnames + programming_languages

    for row in rows:
        content = row['content']
        for lang in programming_languages:
            if re.search(r'\b{}\b'.format(re.escape(lang)), content, flags=re.IGNORECASE):
                row[lang] = '1'
            else:
                row[lang] = '0'

    with open(output_csv, 'w', newline='', encoding='utf-8') as output_file:
        writer = csv.DictWriter(output_file, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)


# %% [markdown]
# # Trình độ 

# %%
def addLevelToCSV(input_csv, level_file, output_csv):
    with open(input_csv, 'r', newline='', encoding='utf-8') as input_file:
        reader = csv.DictReader(input_file)
        rows = list(reader)

    with open(level_file, 'r', encoding='utf-8') as lang_file:
        levels = [line.strip() for line in lang_file]

    fieldnames = reader.fieldnames + levels

    for row in rows:
        content = row['content']
        for lang in levels:
            if re.search(r'\b{}\b'.format(re.escape(lang)), content, flags=re.IGNORECASE):
                row[lang] = '1'
            else:
                row[lang] = '0'

    with open(output_csv, 'w', newline='', encoding='utf-8') as output_file:
        writer = csv.DictWriter(output_file, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)


# %% [markdown]
# # Ngoại ngữ yêu cầu 

# %%
def addLanguagesRequirementToCSV(input_csv, output_csv):
    # Danh sách các từ khóa hoặc biểu thức chính quy cho mỗi ngôn ngữ
    languages_keywords = {
        'Tiếng Anh': r'\b(English|Eng|AV|Anh)\b',
        'Tiếng Nhật': r'\b(Japanese|Japanse|Nhật)\b',
        'Tiếng Trung': r'\b(Chinese|Trung)\b'
    }

    with open(input_csv, 'r', newline='', encoding='utf-8') as input_file:
        reader = csv.DictReader(input_file)
        rows = list(reader)

    # Tạo danh sách các trường mới cho các ngôn ngữ
    languages = list(languages_keywords.keys())

    fieldnames = reader.fieldnames + languages

    for row in rows:
        content = row['content']
        for lang, keyword_pattern in languages_keywords.items():
            if re.search(keyword_pattern, content, flags=re.IGNORECASE):
                row[lang] = '1'
            else:
                row[lang] = '0'

    with open(output_csv, 'w', newline='', encoding='utf-8') as output_file:
        writer = csv.DictWriter(output_file, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)

# %% [markdown]
# # Mảng chuyên

# %%
def addMajorsToCSV(input_csv, major_file, output_csv):
    with open(input_csv, 'r', newline='', encoding='utf-8') as input_file:
        reader = csv.DictReader(input_file)
        rows = list(reader)

    with open(major_file, 'r', encoding='utf-8') as lang_file:
        majors= [line.strip() for line in lang_file]

    fieldnames = reader.fieldnames + majors
    for row in rows:
        content = row['content']
        for lang in majors:
            if re.search(r'\b{}\b'.format(re.escape(lang)), content, flags=re.IGNORECASE):
                row[lang] = '1'
            else:
                row[lang] = '0'

    with open(output_csv, 'w', newline='', encoding='utf-8') as output_file:
        writer = csv.DictWriter(output_file, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)


# %% [markdown]
# # Lương

# %%
def extract_salary(text):
    patterns = [
    r'(?P<min_salary>\d{1,2}(?:\.\d{3})?)\s*-\s*(?P<max_salary>\d{1,2}(?:\.\d{3})?)\s*m',
    r'(?P<min_salary>\d{1,2}(?:\.\d{3})?)\s*-\s*(?P<max_salary>\d{1,2}(?:\.\d{3})?)\s*tr',
    r'(?P<min_salary>\d{1,2}(?:\.\d{3})?)\s*triệu\s*-\s*(?P<max_salary>\d{1,2}(?:\.\d{3})?)\s*triệu',
    r'\b(?P<min_salary>\d{1,2})\s*million\s*-\s*(?P<max_salary>\d{1,2})\s*million\b',
    r'\b(?P<min_salary>\d{1,2})\s*m\s*-\s*(?P<max_salary>\d{1,2})\s*m\b',
    r'\b(?P<min_salary>\d{1,2})\s*triệu\s*-\s*(?P<max_salary>\d{1,2})\s*triệu\b',
    r'\b(?P<min_salary>\d{1,2})\s*-\s*(?P<max_salary>\d{1,2})\s*m\b',
    r'\b(?P<min_salary>\d{1,2})\s*-\s*(?P<max_salary>\d{1,2})\s*tr\b',
    r'\b(?P<min_salary>\d{1,2})\s*-\s*(?P<max_salary>\d{1,2})\s*triệu\b',
    r'\b(?P<min_salary>\d{1,2})\s*-\s*(?P<max_salary>\d{1,2})\s*đ\b',
    r'\b(?P<min_salary>\d{1,2})\s*-\s*(?P<max_salary>\d{1,2})\s*đồng\b',
    r'\b(?P<min_salary>\d{1,2})\s*tr\s*-\s*(?P<max_salary>\d{1,2})\s*tr\b',
    r'(?P<min_salary>\d{1,2}(?:\.\d{3})?)\s*-\s*(?P<max_salary>\d{1,2}(?:\.\d{3})?)\s*vnd',
    r'\b(?P<min_salary>\d{1,2})\s*triệu\s*đến\s*(?P<max_salary>\d{1,2})\s*triệu\b',
    r'(?P<min_salary>\d{1,2}(?:\.\d{3})?)\s*vnd\s*-\s*(?P<max_salary>\d{1,2}(?:\.\d{3})?)vnd',
    r'(?P<min_salary>\d{1,2})m\s*-(?P<max_salary>\d{1,2})m\b',
    r'\b(?P<min_salary>\d{1,2})\s*đến\s*(?P<max_salary>\d{1,2})\s*triệu\b',
    r'\b(?P<min_salary>\d{1,2})\s*đến\s*(?P<max_salary>\d{1,2})\s*tr\b',
    r'(?P<min_salary>\d{1,2}(?:\.\d{3})?)\s*-\s*(?P<max_salary>\d{1,2}(?:\.\d{3})?)',
    r'(?:up\s*to)?\s*(?P<max_salary>\d{1,2}(?:\.\d{3})?)\s*m',
    r'\$(?P<min_salary>\d{1,2}(?:\.\d{3})?)\s*-\s*\$(?P<max_salary>\d{1,2}(?:\.\d{3})?)',
    r'(?P<min_salary>\d{1,2}(?:\.\d{3})?)\$\s*-\s*(?P<max_salary>\d{1,2}(?:\.\d{3})?)\$',
    r'(?P<min_salary>\d{1,2}(?:\.\d{3})?)\s*-\s*\$(?P<max_salary>\d{1,2}(?:\.\d{3})?)',
    r'\$(?P<min_salary>\d{1,2}(?:\.\d{3})?)\s*-\s*(?P<max_salary>\d{1,2}(?:\.\d{3})?)',
    r'minusd(?P<min_salary>\d{1,2}(?:\.\d{3})?)\s*-\s*maxusd(?P<max_salary>\d{1,2}(?:\.\d{3})?)',
    r'(?P<min_salary>\d{1,2}(?:\.\d{3})?)\s*USD\s*-\s*(?P<max_salary>\d{1,2}(?:\.\d{3})?)\s*USD',
    r'(?P<min_salary>\d{1,2})\s*-\s*(?P<max_salary>\d{1,2})\s*USD',
    r'(?P<min_salary>\d{1,2})\s*USD\s*-\s*(?P<max_salary>\d{1,2})\s*USD',
    r'(?:\bup\s*to|\bupto)\s*(?P<max_salary>\d{1,2}(?:\.\d{3})?)\s*tr',
    r'(?:\bup\s*to|\bupto)\s*(?P<max_salary>\d{1,2}(?:\.\d{3})?)\s*triệu',
    r'(?:\bup\s*to|\bupto)\s*(?P<max_salary>\d{1,2}(?:\.\d{3})?)\s*usd',
    r'lên\s+đến|up\s+to\s*\$?(?P<max_salary>\d{1,3}(?:,\d{3})*|\d{1,3})\s*usd?',
    r'lên\s+đến|up\s+to\s*(?P<max_salary>\d{1,2}(?:\.\d{3})*(?:\.\d+)?)\s*vnđ',
    r'lên\s+đến|up\s+to\s*(?P<max_salary>\d{1,2}(?:\.\d{3})*(?:\.\d+)?)\s*vnd',
    r'(?:upto|up\s*to)\s*(?P<max_salary>\d{1,3}(?:\.\d{3})*(?:\.\d+)?)\s*m'
]


    
    for pattern in patterns:
        matches = re.findall(pattern, text.lower())
        if matches:
            if len(matches[0]) == 2:  # Nếu có cả minSalary và maxSalary
                min_salary = float(matches[0][0]) * 1000000 if 'triệu' in pattern or 'm' in pattern or 'tr' in pattern or  '.000.000 ' in pattern else float(matches[0][0]) * 25000
                max_salary = float(matches[0][1]) * 1000000 if 'triệu' in pattern or 'm' in pattern or 'tr' in pattern or '.000.000 ' in pattern else float(matches[0][1]) * 25000
                avg_salary = (min_salary + max_salary) / 2
                return min_salary, max_salary, avg_salary
            elif len(matches[0]) == 1:  # Nếu chỉ có maxSalary
                max_salary = float(matches[0][0]) * 1000000 if 'triệu' or 'm' in pattern or 'tr' in pattern in pattern or '.000.000 ' in pattern  else float(matches[0][0]) * 25000
                return 0, max_salary, 0
    return 0, 0, 0



def add_salary_columns(input_csv_file, output_csv_file):
    with open(input_csv_file, 'r', encoding='utf-8') as input_file:
        reader = csv.DictReader(input_file)
        rows = list(reader)

    for row in rows:
        min_salary, max_salary, avg_salary = extract_salary(row['content'])
        row['minSalary'] = min_salary
        row['maxSalary'] = max_salary
        row['avgSalary'] = avg_salary

    with open(output_csv_file, 'w', newline='', encoding='utf-8') as output_file:
        fieldnames = reader.fieldnames + ['minSalary','maxSalary','avgSalary']
        writer = csv.DictWriter(output_file, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)

# %% [markdown]
# # Năm kinh nghiệm

# %%
def extract_experience(text):
    pattern = r'(\d+)\s*(?=\s*năm)'
    experience = re.findall(pattern, text)
    return experience[0] if experience else '-1'
def update_analyzed_data_with_experience(input_csv_file, output_csv_file):
    with open(input_csv_file, 'r', encoding='utf-8') as input_file:
        reader = csv.DictReader(input_file)
        rows = list(reader)

    for row in rows:
        row['experience'] = extract_experience(row['content'].lower())

    with open(output_csv_file, 'w', newline='', encoding='utf-8') as output_file:
        fieldnames = ['post_id', 'content', 'images', 'email', 'location','minSalary', 'maxSalary', 'avgSalary' ,'experience']
        writer = csv.DictWriter(output_file, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)


# %% [markdown]
# # Trích điện thoại

# %%
def extract_phonenumber(text):
    pattern = r'(?<!\d)(?:0|\+84|84)(?:\d(?:\s|\.)?){9,10}\b'
    phone_numbers = re.findall(pattern, text)
    return phone_numbers[0] if phone_numbers else 'Unknown'
def addPhonenumberToCSV(input_csv_file, output_csv_file):
    with open(input_csv_file, 'r', encoding='utf-8') as input_file:
        reader = csv.DictReader(input_file)
        rows = list(reader)

    for row in rows:
        row['phonenumber'] = extract_phonenumber(row['content'])

    with open(output_csv_file, 'w', newline='', encoding='utf-8') as output_file:
        fieldnames = ['post_id', 'content', 'images', 'email', 'location', 'minSalary', 'maxSalary', 'avgSalary', 'experience', 'phonenumber']
        writer = csv.DictWriter(output_file, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)


# %%
input_csv = 'output1.csv'  
# locations_file = './Vietnamese-data/Vietnam GIS data/Vnlocations.txt' 
programming_languages_file = './Vietnamese-data/ProgramLanguages.txt'
# languages_requirement_file = './Vietnamese-data/ForeginLanguagues'
majors_file = './Vietnamese-data/Major.txt'
level_file = './Vietnamese-data/Level.txt'
output_csv = 'analyzed_data.csv'  
locations_json = './Vietnamese-data/vietname-gis.json'


# %%
# add_location_to_csv(input_csv, locations_file, output_csv)
add_location_to_csv(input_csv,locations_json,output_csv)
update_analyzed_data_with_email(output_csv, output_csv)
add_salary_columns(output_csv,output_csv)
update_analyzed_data_with_experience(output_csv,output_csv)
addPhonenumberToCSV(output_csv,output_csv)
addProgrammingLanguagesToCSV(output_csv, programming_languages_file, output_csv)
addLevelToCSV(output_csv,level_file,output_csv)
addLanguagesRequirementToCSV(output_csv,output_csv)
addMajorsToCSV(output_csv,majors_file,output_csv)

# %% [markdown]
# # Training model 

# %%
data = pd.read_csv("analyzed_data.csv")
data.columns

# %%
features =  ['location', 'experience', 'phonenumber', 'Python', 'Java',
       'JavaScript', 'C++', 'C#', 'PHP', 'Ruby', 'Swift', 'TypeScript', 'Go',
       'Kotlin', 'Rust', 'Lua', 'Perl', 'SQL', 'HTML', 'CSS', 'R', 'MATLAB',
       'Shell', 'Assembly', '.NET', 'C', 'Intern', 'Fresher', 'Senior',
       'Junior', 'Middle', 'Associate', 'Manager', 'Director', 'Principal',
       'Lead', 'Web',
       'Android', 'IOS', 'Backend', 'Frontend', 'Machine Learning', 'Data',
       'Game', 'Embedded', 'Network', 'Computer Science', 'Software',
       'Security', 'Robot', 'Cloud', 'AI', 'Nhúng', 'Bridge', 'Software.1',
       'Designer', 'Scrum', 'BrSE', 'Tester', 'Comtor']

X = data[features]
X.head()



y = data['maxSalary']
y.head()

from sklearn.model_selection import train_test_split
print(data.columns)

# %%
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LinearRegression
from sklearn.tree import DecisionTreeRegressor

# data_filtered = data.loc[data['maxSalary'] != 0]
data_filtered = data.loc[(data['maxSalary'] != 0) &
                     #      &((data['Python'] == 1) | (data['Java'] == 1) | (data['JavaScript'] == 1) | 
                     #      (data['C++'] == 1) | (data['C#'] == 1) | (data['PHP'] == 1) | 
                     #      (data['Ruby'] == 1) | (data['Swift'] == 1) | (data['TypeScript'] == 1) | 
                     #      (data['Go'] == 1) | (data['Kotlin'] == 1) | (data['Rust'] == 1) | 
                     #      (data['Lua'] == 1) | (data['Perl'] == 1) | (data['SQL'] == 1) | 
                     #      (data['HTML'] == 1) | (data['CSS'] == 1) | (data['R'] == 1) | 
                     #      (data['MATLAB'] == 1) | (data['Shell'] == 1) | (data['Assembly'] == 1) | 
                     #      (data['.NET'] == 1) | (data['C'] == 1)) &
                         ((data['Intern'] == 1) | (data['Fresher'] == 1) | (data['Senior'] == 1) | 
                          (data['Junior'] == 1) | (data['Middle'] == 1) | (data['Associate'] == 1) | 
                          (data['Manager'] == 1) | (data['Director'] == 1) | (data['Principal'] == 1) | 
                          (data['Lead'] == 1))]
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
       'Shell', 'Assembly', '.NET', 'C', 'Intern', 'Fresher', 'Senior',
       'Junior', 'Middle', 'Associate', 'Manager', 'Director', 'Principal',
       'Lead', 'Tiếng Anh', 'Tiếng Nhật', 'Tiếng Trung', 'Web', 'Android',
       'IOS', 'Backend', 'Frontend', 'Machine Learning', 'Data', 'Game',
       'Embedded', 'Network', 'Computer Science', 'Software', 'Security',
       'Robot', 'Cloud', 'AI', 'Nhúng', 'Bridge', 'Software.1', 'Designer',
       'Scrum', 'BrSE', 'Tester', 'Comtor']

# Assign features and target
X = data_filtered[features]
y = data_filtered['maxSalary']

# Chia dữ liệu thành tập huấn luyện và tập thử nghiệm
X_train, X_valid, y_train, y_valid = train_test_split(X, y, train_size=0.8, test_size=0.2, random_state=0)


# %%
print("Số lượng dữ liệu huấn luyện:", X_train.shape[0])


# %%
dt_model = DecisionTreeRegressor(random_state = 1 )
dt_model.fit(X_train , y_train)
y_preds = dt_model.predict(X_valid)


# %%
y_preds = dt_model.predict(X_valid)

# %%
pd.DataFrame({'Lương thực tế' : y_valid, 'Lương dự đoán' : y_preds.round()})

# %%
print("Số lượng phần tử trong y_valid:", len(y_valid))
print("Số lượng phần tử trong y_preds:", len(y_preds))

# Tạo DataFrame từ dictionary
df = pd.DataFrame({'y': y_valid, 'y_preds': y_preds.round()})

# Kiểm tra số lượng chỉ mục của DataFrame
print("Số lượng chỉ mục của DataFrame:", len(df))

# %%
# Tạo một mảng số nguyên từ 1 đến số lượng mẫu dữ liệu
index = range(1, len(y_valid) + 1)

# Vẽ biểu đồ so sánh giá trị thực và giá trị dự đoán
plt.figure(figsize=(10, 6))
plt.plot(index, y_valid, label='Thực tế')
plt.plot(index, y_preds, label='Dự đoán')
plt.xlabel('Số thứ tự')
plt.ylabel('Lương')
plt.title('So sánh giá trị thực và dự đoán')
plt.legend()
plt.show()


# %%
from sklearn.ensemble import RandomForestRegressor , GradientBoostingRegressor
rf_model = RandomForestRegressor(random_state= 1 )
rf_model.fit(X_train,y_train)


# %%
rf_val_preds = rf_model.predict(X_valid)
rf_val_preds[:5]
X_valid.head()

# %% [markdown]
# #Liner Regressor Hồi quy tuyến tính

# %%
from sklearn.linear_model import LinearRegression

# Khởi tạo mô hình Linear Regression
lr_model = LinearRegression()

# Huấn luyện mô hình
lr_model.fit(X_train, y_train)

# Dự đoán
y_preds_lr = lr_model.predict(X_valid)
pd.DataFrame({'Lương thực tế' : y_valid, 'Lương dự đoán' : y_preds_lr.round(), 'Sai số':y_valid-y_preds_lr.round()})



from sklearn.ensemble import RandomForestRegressor

# Khởi tạo mô hình Random Forest Regression
rf_model = RandomForestRegressor(random_state=1)

# Huấn luyện mô hình
rf_model.fit(X_train, y_train)

# Dự đoán
y_preds_rf = rf_model.predict(X_valid)
pd.DataFrame({'Lương thực tế' : y_valid, 'Lương dự đoán' : y_preds_rf.round() , 'Sai số': y_valid-y_preds_rf.round()})

# %% [markdown]
# # Gradient Boosting Regression

