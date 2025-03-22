# GitHub User Info Fetcher

A terminal-based Kotlin application that retrieves GitHub user information via the GitHub API and caches the data to prevent redundant requests.

## 📌 Project Info

- **Student Name:** Saeed Forati-Kashani  
- **Student ID:** 401106299
- **Course:** Mobile Programming 
- **Instructor:** Alireza Delavar & Pouya Yarandi

## 📌 Project Objective

This project aims to build a Kotlin terminal application that fetches user details from GitHub, stores them in memory, and allows efficient searching and retrieval. The user details include:

- Username  
- Number of followers  
- Number of following  
- Account creation date  
- List of public repositories  

## 📝 Features

- ✅ Fetch user information by username  
- ✅ Display the list of cached users  
- ✅ Search for a user in the cached data by username  
- ✅ Search for a repository by name within cached data  
- ✅ Exit the application  

## 🛠️ Technical Details

- Uses the [GitHub REST API](https://docs.github.com/en/rest?apiVersion=2022-11-28) for fetching user data.  
- Implements **in-memory caching** to avoid duplicate API requests.  
- Optionally, data can be stored in a file instead of memory.  
- Handles API errors gracefully.  
- Encourages **scalability and maintainability** using design patterns and Kotlin best practices.  
- Utilizes **Retrofit** and **Gson** for API communication and JSON parsing.  

### 🔗 Key Libraries Used:

- **Retrofit**: For making HTTP requests → [Docs](https://square.github.io/retrofit)  
- **Gson**: For JSON parsing  

## 🚀 How to Run

1. Clone this repository:  
   ```sh
   git clone https://github.com/foratik/GitHub-User-info.git
   cd GitHub-User-info
   ```
2. Ensure you have Kotlin installed.

3. Run the application:

Linux / MacOS:
  ```sh
  chmod +x gradlew
  ./gradlew build
  ./gradlew run
  ```

Windows:
```batch
gradlew.bat build
gradlew.bat run
```

## 📦 Program Menu
```plaintext
📦 Program Menu:
1️⃣ Get user information by username
2️⃣ Show list of cached users
3️⃣ Search for a user in the cache by username
4️⃣ Search for a repository by name
5️⃣ Exit
```


