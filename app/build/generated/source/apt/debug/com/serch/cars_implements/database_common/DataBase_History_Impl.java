package com.serch.cars_implements.database_common;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import com.serch.cars_implements.database_history.Dao_history;
import com.serch.cars_implements.database_history.Dao_history_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class DataBase_History_Impl extends DataBase_History {
  private volatile Dao_history _daoHistory;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `history_table` (`id_history` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `history_id_number` INTEGER, `history_name` TEXT, `history_apel` TEXT, `history_car` TEXT, `history_date` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"b79663b69aa9002aa0c4e0e2307d472c\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `history_table`");
      }

      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsHistoryTable = new HashMap<String, TableInfo.Column>(6);
        _columnsHistoryTable.put("id_history", new TableInfo.Column("id_history", "INTEGER", true, 1));
        _columnsHistoryTable.put("history_id_number", new TableInfo.Column("history_id_number", "INTEGER", false, 0));
        _columnsHistoryTable.put("history_name", new TableInfo.Column("history_name", "TEXT", false, 0));
        _columnsHistoryTable.put("history_apel", new TableInfo.Column("history_apel", "TEXT", false, 0));
        _columnsHistoryTable.put("history_car", new TableInfo.Column("history_car", "TEXT", false, 0));
        _columnsHistoryTable.put("history_date", new TableInfo.Column("history_date", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysHistoryTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesHistoryTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoHistoryTable = new TableInfo("history_table", _columnsHistoryTable, _foreignKeysHistoryTable, _indicesHistoryTable);
        final TableInfo _existingHistoryTable = TableInfo.read(_db, "history_table");
        if (! _infoHistoryTable.equals(_existingHistoryTable)) {
          throw new IllegalStateException("Migration didn't properly handle history_table(com.serch.cars_implements.database_history.History).\n"
                  + " Expected:\n" + _infoHistoryTable + "\n"
                  + " Found:\n" + _existingHistoryTable);
        }
      }
    }, "b79663b69aa9002aa0c4e0e2307d472c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "history_table");
  }

  @Override
  public Dao_history dao_history() {
    if (_daoHistory != null) {
      return _daoHistory;
    } else {
      synchronized(this) {
        if(_daoHistory == null) {
          _daoHistory = new Dao_history_Impl(this);
        }
        return _daoHistory;
      }
    }
  }
}
