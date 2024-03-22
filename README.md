# PBL3 Applying AI to analyze recruitment information on Facebook
### 1. Cài đặt
1. Mở CMD trên máy gõ :
```python
git clone https://github.com/Annguyn/PBL3-Applying-AI-to-analyze-recruitment-information-on-Facebook/
```
để clone project về máy

2. Cài đặt các thư viện python cần thiết.
# Cài đặt 'requests'
```python
    pip install requests
```
  # Cài đặt pyotp
```python
    pip install pyotp
```
  # Cài đặt Selenium
  ```python
  pip install selenium
  ```
  # Cài đặt Pandas
  ```python
  pip install pandas
  ```

  # Cài đặt Keyboard
  ```python
  pip install keyboard
  ```
  # Cài đặt Chromedriver 
  ```python
  Install-Package Selenium.WebDriver.ChromeDriver -Version 122.0.6261.11100
  ```
### 2. Sử dụng
#### 1. Thay đổi thông tin tài khoản và mật khẩu của bạn
```python
userName = '100054522522135'
passWord = 'GtT71qOMzwgLJVe'
```
#### 2. Chạy chương trình
```python
python crawl.py
```
Sau khi chương trình tự đăng nhập , chọn 1 để crawl id các bài post trong group  , chọn 2 để đọc text của các id đã được crawl ở bước 1.
-> Nhập số lượng bài viết cần crawl.
