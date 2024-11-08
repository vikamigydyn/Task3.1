## Лабораторная работа №3.1. Работа с базой данных.
## Выполнила: Мигыдын Вика, ИСП-211о

## 1. Обзор
Приложение состоит из двух активностей: основной активности для добавления и обновления студентов и второй активности для отображения списка студентов.

## 2. Структура
- **MainActivity**: Основная активность, содержащая кнопки для добавления нового студента, обновления последнего студента и перехода к списку студентов.
  
![скрин1 (1)](https://github.com/user-attachments/assets/0b66a4f2-7e35-4da7-9175-7a7bf5ed40fd)

- **MainActivity2**: Вторая активность, отображающая список студентов с их именами и временем добавления.

![скрин2 (1)](https://github.com/user-attachments/assets/df3d0db1-912f-41fa-aa30-e9c773a38a52)

## 3. MainActivity
На данном экране расположены кнопки:
- **Кнопка 1 "Посмотреть"**: При нажатии запускает `MainActivity2` для отображения списка студентов.
  
`btnShowRecords.setOnClickListener(v -> {
  Intent intent = new Intent(MainActivity.this, MainActivity2.class);
  startActivity(intent);
  });`
- **Кнопка 2 "Добавить"**: При нажатии добавляет нового студента с именем "Новый Одногруппник" в базу данных.
  
` btnAddRecord.setOnClickListener(v -> {
  ContentValues contentValues = new ContentValues();
  contentValues.put("FIO", "Новый Одногруппник");
  long newRowId = db.insert("classmates", null, contentValues);
  if (newRowId != -1) {
  Toast.makeText(MainActivity.this, "Одногруппник добавлен", Toast.LENGTH_SHORT).show();
  } else {
  Toast.makeText(MainActivity.this, "Ошибка добавления одногруппника", Toast.LENGTH_SHORT).show();
  }
  });`

  ![скрин3 (1)](https://github.com/user-attachments/assets/882a55a4-456c-49ca-8ac8-3aa98c2c55d3)

![скрин4](https://github.com/user-attachments/assets/a88480fc-7e7d-4f1a-89d9-62bcf55368f0)

- **Кнопка 3 "Обновить"**: При нажатии обновляет информацию о последнем добавленном студенте на "Иванов Иван Иванович".
  
`private boolean updateLastRecord() {
  Cursor cursor = db.rawQuery("SELECT * FROM classmates ORDER BY ID DESC LIMIT 1", null);
  boolean updated = false;
  if (cursor != null && cursor.moveToFirst()) {
  @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("ID"));
  ContentValues contentValues = new ContentValues();
  contentValues.put("FIO", "Иванов Иван Иванович");
  int rowsAffected = db.update("classmates", contentValues, "ID = ?", new String[]{String.valueOf(id)});
  updated = rowsAffected > 0;
  }
  cursor.close();
  return updated;
  }`

  ![скрин5 (1)](https://github.com/user-attachments/assets/58b461ac-d85c-40d4-b568-a6811a162c13)

![скрин6 (1)](https://github.com/user-attachments/assets/3075af30-8827-445f-b9bb-86d1afd4b8a8)

## 4. MainActivity2
Второй экран создан для отображения списка студентов. Он состоит из `ListView`, который заполняется данными из базы данных:
- Список студентов отображает имя и время добавления каждого студента.
## 5. База данных
Приложение использует SQLite для хранения информации о студентах. При первом запуске создается таблица `classmates`, в которую добавляются 5 студентов с именами "Одногруппник 1" до "Одногруппник 5".
