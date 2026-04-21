# Note of Java mini-project
## Features:
**Data-Show:**
    [====Table header====]
- Title
- Release
- Ratting
- Runtime
- Budget
- Genres
- Origin
**Pagination**
- Show records Data of movies 10 rows.
- Can go to next page [n] and back [b]
- previous page [p] and exit [e]
- Go To[g]
- Movies Details [md]

---

## 🎬 Movie Console Application (TMDB)

This application allows users to search and explore movies using **The Movie Database (TMDB) API** through a console interface.

---

## 🚀 Features & Modes

### 🔍 Search Mode
- Enter a movie title
- Displays a paginated list of results

```bash
Enter movie → show results
````

## 🔥Trending Mode
- View trending movies (no search required)
```bash
[tr] → show trending movies
```

## 🎬 Popular / Top Rated / Now Playing
- Browse movies by category 
- Supports pagination
```bash
[pop] → show popular movies  
[top] → show top rated movies  
[now] → show now playing movies  
```

## 🎯 Similar Movies
- Show movies related to a selected movie
```bash
[sim] → enter movie ID → show similar movies
```

## 🎭 Cast
- Display main cast of a movie
```bash
[c] → enter movie ID → show cast list
```
## 📄 Movie Detail
- View detailed movie information 
- Shows only detail (no list refresh)
```bash
[md] → enter movie ID → show movie detail 
```

## 🧭 Navigation Commands
```bash
[n] → next page  
[p] → previous page  
[g] → go to page (e.g., g 3, g 4)  
[s] → new search  
[e] → exit  
```

## Notes
- Pagination works for search, popular, top rated, and now playing

- Trending, similar, and cast do not use pagination

- Trailer is fetched only when needed for better performance

## 📦 Tech Stack
- Java (Console Application)

- TMDB API

- HTTP Client (Java 11+)

- org.json

## 🏗️ Project Structure
```
src/
 ├── model/
 │    ├── Movies.java
 │    └── MovieDetail.java
 │
 ├── services/
 │    ├── MovieService.java
 │    └── MovieServiceImpl.java
 │
 ├── utils/
 │    ├── ApiClient.java
 │    ├── TableRender.java
 │    └── Ascii.java
 │
 └── Application.java
```









[//]: # (```)

[//]: # (   Access Token= eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxOTVmNWFmZTE0ZWVjZGE0NWI4ZDUwMWJjODFlOTZjOSIsIm5iZiI6MTc3NjY4NTI3MC44MDQsInN1YiI6IjY5ZTYxMGQ2ZTIzMWM4MjFmYzA2NDMwNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.lgBMUcRvOozqDLttX_cZkNSrt1gxCBKd6zNmTE7BuNs)

[//]: # (   API Key= 195f5afe14eecda45b8d501bc81e96c9)

[//]: # (```)