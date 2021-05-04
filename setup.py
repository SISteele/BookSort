import mysql.connector
from mysql.connector import Error
import pandas as pd
import pyodbc

# Connect to SQL server. ENTER YOUR SERVER NAME WHERE IT SAYS 'Server=SERVERNAME;'
conn = pyodbc.connect('Driver={SQL Server};'
                      'Server=SERVERNAME;'
                      'Database=TermDB;'
                      'Trusted_Connection=yes;')
cursor = conn.cursor()


# Set up CSV files for SQL
ratingsRaw = pd.read_csv (r'./ratings.csv')
ratings = pd.DataFrame(data, columns= ['user_id', 'book_id', 'rating'])

to_readRaw = pd.read_csv (r'./to_read')
to_read = pd.DataFrame(data, columns= ['user_id', 'book_id'])

booksRaw = pd.read_csv (r'./books.csv')
books = pd.DataFrame(data, columns= ['goodreads_book_id', 'book_id', 'best_book_id','work_id', 'books_count', 'isbn', 'authors', 'original_publication_year', 'original_title','title', 'language_code', 'average_rating', 'ratings_count', 'work_ratings_count', 'work_text_reviews_count', 'ratings_1', 'ratings_2', 'ratings_3', 'ratings_4', 'ratings_5'])

book_tagsRaw = pd.read_csv (r'./book_tags.csv')
book_tags = pd.DataFrame(data, columns= ['goodreads_book_id', 'tag_id', 'count'])

tagsRaw = pd.read_csv (r'./tags.csv')
tags = pd.DataFrame(data, columns= ['tag_id', 'tag_name'])


# Create tables to import CSV data into
cursor.execute('CREATE TABLE ratings (user_id INT(11), book_id INT(11), rating INT(11))')
cursor.execute('CREATE TABLE to_read (user_id INT(11), book_id INT(11)')
cursor.execute('CREATE TABLE books (goodreads_book_id INT(11), book_id INT(11), best_book_id INT(11), work_id INT(11), books_count INT(11), isbn INT(11), authors VARCHAR(100), original_publication_year INT(11), original_title VARCHAR(45), title VARCHAR(45), language_code VARCHAR(45), average_rating FLOAT, ratings_count INT(11), work_ratings_count INT(11), work_text_reviews_count INT(11), ratings_1 INT(11), ratings_2 INT(11), ratings_3 INT(11), ratings_4 INT(11), ratings_5 INT(11))')
cursor.execute('CREATE TABLE book_tags (goodreads_book_id INT(11), tag_id INT(11), count INT(11))')
cursor.execute('CREATE TABLE tags (tag_id INT(11), tag_name VARCHAR(45))')


# Insert the dataframes into the respective tables
for row in ratings.itertuples():
    cursor.execute('''
                   INSERT INTO TermDB.dbo.ratings (user_id, book_id, rating)
                   VALUES (?,?,?)
                   ''',
                   row.user_id,
                   row.book_id,
                   row.rating
                   )

for row in to_read.itertuples():
    cursor.execute('''
                  INSERT INTO TERM.dbo.to_read (goodreads_book_id, tag_id, count)
                  VALUES (?,?,?)
                  ''',
                  row.goodreads_book_id,
                  row.tag_id,
                  row.count
                  )

for row in books.itertuples():
    cursor.execute('''
                  INSERT INTO TERM.dbo.books (goodreads_book_id, book_id, best_book_id, work_id, books_count, isbn, authors, original_publication_year, original_title,title, language_code, average_rating, ratings_count, work_ratings_count, work_text_reviews_count, ratings_1, ratings_2, ratings_3, ratings_4, ratings_5)
                  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                  ''',
                  row.goodreads_book_id,
                  row.book_id,
                  row.best_book_id,
                  row.work_id,
                  row.books_count,
                  row.isbn,
                  row.authors,
                  row.original_publication_year,
                  row.original_title,
                  row.title,
                  row.language_code,
                  row.average_rating,
                  row.ratings_count,
                  row.work_ratings_count,
                  row.work_text_reviews_count,
                  row.ratings_1,
                  row.ratings_2,
                  row.ratings_3,
                  row.ratings_4,
                  row.ratings_5
                  )

for row in book_tags.itertuples():
    cursor.execute('''
                  INSERT INTO TERM.dbo.book_tags (goodreads_book_id, tag_id, count)
                  VALUES (?,?,?)
                  ''',
                  row.goodreads_book_id,
                  row.tag_id,
                  row.count
                  )

for row in tags.itertuples():
    cursor.execute('''
                  INSERT INTO TERM.dbo.tags (tag_id, tag_name)
                  VALUES (?,?)
                  ''',
                  row.tag_id,
                  row.tag_name
                  )

conn.commit()
