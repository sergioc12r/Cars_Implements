package com.serch.cars_implements.database_history;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class Dao_history_Impl implements Dao_history {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfHistory;

  public Dao_history_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHistory = new EntityInsertionAdapter<History>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `history_table`(`id_history`,`history_id_number`,`history_name`,`history_apel`,`history_car`,`history_date`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, History value) {
        stmt.bindLong(1, value.getId_history());
        if (value.getId_number() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getId_number());
        }
        if (value.getName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getName());
        }
        if (value.getApel() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getApel());
        }
        if (value.getCar() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getCar());
        }
        if (value.getDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDate());
        }
      }
    };
  }

  @Override
  public void addHistory(History history) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfHistory.insert(history);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<History> getHistory() {
    final String _sql = "select * from history_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfIdHistory = _cursor.getColumnIndexOrThrow("id_history");
      final int _cursorIndexOfIdNumber = _cursor.getColumnIndexOrThrow("history_id_number");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("history_name");
      final int _cursorIndexOfApel = _cursor.getColumnIndexOrThrow("history_apel");
      final int _cursorIndexOfCar = _cursor.getColumnIndexOrThrow("history_car");
      final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("history_date");
      final List<History> _result = new ArrayList<History>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final History _item;
        _item = new History();
        final int _tmpId_history;
        _tmpId_history = _cursor.getInt(_cursorIndexOfIdHistory);
        _item.setId_history(_tmpId_history);
        final Long _tmpId_number;
        if (_cursor.isNull(_cursorIndexOfIdNumber)) {
          _tmpId_number = null;
        } else {
          _tmpId_number = _cursor.getLong(_cursorIndexOfIdNumber);
        }
        _item.setId_number(_tmpId_number);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        _item.setName(_tmpName);
        final String _tmpApel;
        _tmpApel = _cursor.getString(_cursorIndexOfApel);
        _item.setApel(_tmpApel);
        final String _tmpCar;
        _tmpCar = _cursor.getString(_cursorIndexOfCar);
        _item.setCar(_tmpCar);
        final String _tmpDate;
        _tmpDate = _cursor.getString(_cursorIndexOfDate);
        _item.setDate(_tmpDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
