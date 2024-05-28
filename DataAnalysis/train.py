from sklearn.base import BaseEstimator, RegressorMixin
from sklearn.linear_model import LinearRegression
import pandas as pd
from sklearn.model_selection import train_test_split

class LinearModel(BaseEstimator, RegressorMixin):
    def __init__(self):
        self.model = LinearRegression()

    def fit(self, X, y):
        # Fit the linear regression model only on 'level_number' and 'experience'
        self.model.fit(X[['level_number', 'experience']], y)
        return self

    def predict(self, X):
        # Make predictions using the fitted linear regression model
        return self.model.predict(X[['level_number', 'experience']])
# Load the old CSV file
old_data = pd.read_csv("C:\\Users\\nguye\\OneDrive - The University of Technology\\GITHUB CLONE REPO\\PBL3-Applying-AI-to-analyze-recruitment-information-on-Facebook\\DataAnalysis\\analyzed_data.csv")

# Filter the old data
old_data_filtered = old_data.loc[(old_data['maxSalary_std'] != 0) &
                                (old_data['level_number'] >= 1) &
                                (old_data['experience'] >= 1) & 
                                (old_data['experience'] <= 30) & 
                                (old_data['location_number'] != 0)]

# Assign features and target
X_old = old_data_filtered[['experience', 'level_number']]
y_old = old_data_filtered['maxSalary_std']

# Split the old data into training and validation sets
X_old_train, X_old_valid, y_old_train, y_old_valid = train_test_split(X_old, y_old, train_size=0.8, test_size=0.2, random_state=0)

# Create an instance of the LinearModel class
linear_model_alone = LinearModel()

# Fit the LinearModel using only 'level_number' and 'experience' from the old data
linear_model_alone.fit(X_old_train, y_old_train)

# Predict salaries using the LinearModel on the old data
y_pred_linear_alone_old_train = linear_model_alone.predict(X_old_train)
y_pred_linear_alone_old_valid = linear_model_alone.predict(X_old_valid)

# Evaluate the model on the old data
train_score_linear_alone_old = linear_model_alone.score(X_old_train, y_old_train)
valid_score_linear_alone_old = linear_model_alone.score(X_old_valid, y_old_valid)

# Print scores or use them as needed
import joblib

# Save the model to a file
joblib.dump(linear_model_alone, 'linear_model_alone.pkl')