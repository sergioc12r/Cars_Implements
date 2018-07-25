package com.serch.cars_implements.database_persons;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class Dao_person_Impl implements Dao_person {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPerson;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfPerson;

  private final SharedSQLiteStatement __preparedStmtOfDeletePersonByIDNumber;

  private final SharedSQLiteStatement __preparedStmtOfUpdatePersonByIDNumber;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPersons;

  public Dao_person_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPerson = new EntityInsertionAdapter<Person>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `persons_table`(`id_person`,`person_name`,`person_apel`,`person_born`,`person_id_number`,`person_prof`,`person_married`,`person_gain`,`person_car`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Person value) {
        stmt.bindLong(1, value.getId_person());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getApel() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getApel());
        }
        if (value.getBorn() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getBorn());
        }
        if (value.getId_number() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getId_number());
        }
        if (value.getProf() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getProf());
        }
        final Integer _tmp;
        _tmp = value.getMarried() == null ? null : (value.getMarried() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindLong(7, _tmp);
        }
        if (value.getGain() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindDouble(8, value.getGain());
        }
        if (value.getPlaca_car() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getPlaca_car());
        }
      }
    };
    this.__deletionAdapterOfPerson = new EntityDeletionOrUpdateAdapter<Person>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `persons_table` WHERE `id_person` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Person value) {
        stmt.bindLong(1, value.getId_person());
      }
    };
    this.__preparedStmtOfDeletePersonByIDNumber = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM persons_table WHERE person_id_number= ? ";
        return _query;
      }
    };
    this.__preparedStmtOfUpdatePersonByIDNumber = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE persons_table SET person_name= ?, person_apel = ?, person_born = ?, person_prof= ?, person_married= ?, person_gain = ?, person_car=? WHERE person_id_number = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllPersons = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM persons_table";
        return _query;
      }
    };
  }

  @Override
  public void addPerson(Person person) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPerson.insert(person);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deletePerson(Person person) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfPerson.handle(person);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeletePersonByIDNumber(String id_number) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePersonByIDNumber.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (id_number == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, id_number);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeletePersonByIDNumber.release(_stmt);
    }
  }

  @Override
  public void UpdatePersonByIDNumber(String s_nombres, String s_apellidos, String s_fecha,
      Long s_id_number, String s_prof, Boolean s_married, Double s_gain, String s_vehi) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdatePersonByIDNumber.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (s_nombres == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, s_nombres);
      }
      _argIndex = 2;
      if (s_apellidos == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, s_apellidos);
      }
      _argIndex = 3;
      if (s_fecha == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, s_fecha);
      }
      _argIndex = 4;
      if (s_prof == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, s_prof);
      }
      _argIndex = 5;
      final Integer _tmp;
      _tmp = s_married == null ? null : (s_married ? 1 : 0);
      if (_tmp == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, _tmp);
      }
      _argIndex = 6;
      if (s_gain == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindDouble(_argIndex, s_gain);
      }
      _argIndex = 7;
      if (s_vehi == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, s_vehi);
      }
      _argIndex = 8;
      if (s_id_number == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, s_id_number);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdatePersonByIDNumber.release(_stmt);
    }
  }

  @Override
  public void deleteAllPersons() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPersons.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllPersons.release(_stmt);
    }
  }

  @Override
  public List<Person> getPersons() {
    final String _sql = "select * from persons_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIdPerson = _cursor.getColumnIndexOrThrow("id_person");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("person_name");
      final int _cursorIndexOfApel = _cursor.getColumnIndexOrThrow("person_apel");
      final int _cursorIndexOfBorn = _cursor.getColumnIndexOrThrow("person_born");
      final int _cursorIndexOfIdNumber = _cursor.getColumnIndexOrThrow("person_id_number");
      final int _cursorIndexOfProf = _cursor.getColumnIndexOrThrow("person_prof");
      final int _cursorIndexOfMarried = _cursor.getColumnIndexOrThrow("person_married");
      final int _cursorIndexOfGain = _cursor.getColumnIndexOrThrow("person_gain");
      final int _cursorIndexOfPlacaCar = _cursor.getColumnIndexOrThrow("person_car");
      final List<Person> _result = new ArrayList<Person>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Person _item;
        _item = new Person();
        final int _tmpId_person;
        _tmpId_person = _cursor.getInt(_cursorIndexOfIdPerson);
        _item.setId_person(_tmpId_person);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpApel;
        _tmpApel = _cursor.getString(_cursorIndexOfApel);
        _item.setApel(_tmpApel);
        final String _tmpBorn;
        _tmpBorn = _cursor.getString(_cursorIndexOfBorn);
        _item.setBorn(_tmpBorn);
        final Long _tmpId_number;
        if (_cursor.isNull(_cursorIndexOfIdNumber)) {
          _tmpId_number = null;
        } else {
          _tmpId_number = _cursor.getLong(_cursorIndexOfIdNumber);
        }
        _item.setId_number(_tmpId_number);
        final String _tmpProf;
        _tmpProf = _cursor.getString(_cursorIndexOfProf);
        _item.setProf(_tmpProf);
        final Boolean _tmpMarried;
        final Integer _tmp;
        if (_cursor.isNull(_cursorIndexOfMarried)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getInt(_cursorIndexOfMarried);
        }
        _tmpMarried = _tmp == null ? null : _tmp != 0;
        _item.setMarried(_tmpMarried);
        final Double _tmpGain;
        if (_cursor.isNull(_cursorIndexOfGain)) {
          _tmpGain = null;
        } else {
          _tmpGain = _cursor.getDouble(_cursorIndexOfGain);
        }
        _item.setGain(_tmpGain);
        final String _tmpPlaca_car;
        _tmpPlaca_car = _cursor.getString(_cursorIndexOfPlacaCar);
        _item.setPlaca_car(_tmpPlaca_car);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
