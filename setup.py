import mysql.connector

ourdb = mysql.connector.connect(
  host="localhost",
)

mycursor = mydb.cursor()

mycursor.execute("CREATE DATABASE ourdatabase")
