import mysql.connector
# from mysql.connector import Error
import pandas as pd
import pyodbc
import os
import time

# # Arpinar's code. Feel free to add or remove as needed.
# username = input('What is your mysql username? ')
# password = input('What is your mysql password? ')
# input_file = 'music.sql'
# mysql_login = 'mysql -u' + username + ' -p' + password + ' < ' + input_file
# os.system(mysql_login)
# os.system('cd MusicApp')
# os.system('javac -cp MusicApp/lib/mysql-connector-java-8.0.16.jar  -d MusicApp/bin MusicApp/src/controller/*.java MusicApp/src/model/*.java MusicApp/src/view/*.java MusicApp/src/model/DAO/*.java')
# time.sleep(20)
# os.system('java MusicApp/bin/controller/MusicApp')

# Connect to SQL server. ENTER YOUR SERVER NAME WHERE IT SAYS 'Server=SERVERNAME;'
mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="",
  db="term_project"
)

print(mydb)

cursor = mydb.cursor()


# Set up CSV files for SQL
ratingsRaw = pd.read_csv (r'./ratings.csv')
ratings = pd.DataFrame(ratingsRaw, columns= ['user_id', 'book_id', 'rating'])

to_readRaw = pd.read_csv (r'./to_read.csv')
to_read = pd.DataFrame(to_readRaw, columns= ['user_id', 'book_id'])

booksRaw = pd.read_csv (r'./books.csv')
booksRaw = booksRaw.where((pd.notnull(booksRaw)), None)
books = pd.DataFrame(booksRaw, columns= ['goodreads_book_id', 'book_id', 'best_book_id','work_id', 'books_count', 'isbn', 'authors', 'original_publication_year', 'original_title','title', 'language_code', 'average_rating', 'ratings_count', 'work_ratings_count', 'work_text_reviews_count', 'ratings_1', 'ratings_2', 'ratings_3', 'ratings_4', 'ratings_5', 'image_url'])

book_tagsRaw = pd.read_csv (r'./book_tags.csv')
book_tags = pd.DataFrame(book_tagsRaw, columns= ['goodreads_book_id', 'tag_id', 'count'])

tagsRaw = pd.read_csv (r'./tags.csv')
tags = pd.DataFrame(tagsRaw, columns= ['tag_id', 'tag_name'])


# Create tables to import CSV data into
cursor.execute('CREATE TABLE IF NOT EXISTS ratings (user_id INT(11), book_id INT(11), rating INT(11))')
cursor.execute('CREATE TABLE IF NOT EXISTS to_read (user_id INT(11), book_id INT(11))')
cursor.execute('CREATE TABLE IF NOT EXISTS books (goodreads_book_id INT(11), book_id INT(11), best_book_id INT(11), work_id INT(11), books_count INT(11), isbn INT(11), authors VARCHAR(100), original_publication_year INT(11), original_title VARCHAR(200), title VARCHAR(200), language_code VARCHAR(45), average_rating FLOAT, ratings_count INT(11), work_ratings_count INT(11), work_text_reviews_count INT(11), ratings_1 INT(11), ratings_2 INT(11), ratings_3 INT(11), ratings_4 INT(11), ratings_5 INT(11), image_url VARCHAR(200))')
cursor.execute('CREATE TABLE IF NOT EXISTS book_tags (goodreads_book_id INT(11), tag_id INT(11), count INT(11))')
cursor.execute('CREATE TABLE IF NOT EXISTS tags (tag_id INT(11), tag_name VARCHAR(45))')


# Insert the dataframes into the respective tables
for row in books.itertuples():
    cursor.execute('''
                  INSERT INTO books (goodreads_book_id, book_id, best_book_id, work_id, books_count, isbn, authors, original_publication_year, original_title, title, language_code, average_rating, ratings_count, work_ratings_count, work_text_reviews_count, ratings_1, ratings_2, ratings_3, ratings_4, ratings_5, image_url)
                  VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)
                  ''',
                  (row.goodreads_book_id,
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
                  row.ratings_5,
                  row.image_url)
                  )

mydb.commit()

cc = 1
for row in ratings.itertuples():
    sql = "INSERT INTO ratings (user_id, book_id, rating) VALUES (%s,%s,%s)"
    val = (row.user_id, row.book_id, row.rating)
    cursor.execute(sql, val)
    print(cc)
    cc = cc + 1

mydb.commit()
for row in to_read.itertuples():
    cursor.execute('''
                  INSERT INTO to_read (user_id, book_id)
                  VALUES (%s,%s)
                  ''',
                  (row.user_id, row.book_id)
                  )


mydb.commit()
for row in book_tags.itertuples():
    cursor.execute('''
                  INSERT INTO book_tags (goodreads_book_id, tag_id, count)
                  VALUES (%s,%s,%s)
                  ''',
                  (row.goodreads_book_id,
                  row.tag_id,
                  row.count)
                  )
mydb.commit()
for row in tags.itertuples():
    cursor.execute('''
                  INSERT INTO tags (tag_id, tag_name)
                  VALUES (%s,%s)
                  ''',
                  (row.tag_id,
                  row.tag_name)
                  )

mydb.commit()
