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
import com.serch.cars_implements.database_persons.Dao_person;
import com.serch.cars_implements.database_persons.Dao_person_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class DataBase_Persons_Impl extends DataBase_Persons {
  private volatile Dao_person _daoPerson;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `persons_table` (`id_person` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `person_name` TEXT, `person_apel` TEXT, `person_born` TEXT, `person_id_number` INTEGER, `person_prof` TEXT, `person_married` INTEGER, `person_gain` REAL, `person_car` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"29f82c41f7b1cdcce8831a5798a5f3c3\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `persons_table`");
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
        final HashMap<String, TableInfo.Column> _columnsPersonsTable = new HashMap<String, TableInfo.Column>(9);
        _columnsPersonsTable.put("id_person", new TableInfo.Column("id_person", "INTEGER", true, 1));
        _columnsPersonsTable.put("person_name", new TableInfo.Column("person_name", "TEXT", false, 0));
        _columnsPersonsTable.put("person_apel", new TableInfo.Column("person_apel", "TEXT", false, 0));
        _columnsPersonsTable.put("person_born", new TableInfo.Column("person_born", "TEXT", false, 0));
        _columnsPersonsTable.put("person_id_number", new TableInfo.Column("person_id_number", "INTEGER", false, 0));
        _columnsPersonsTable.put("person_prof", new TableInfo.Column("person_prof", "TEXT", false, 0));
        _columnsPersonsTable.put("person_married", new TableInfo.Column("person_married", "INTEGER", false, 0));
        _columnsPersonsTable.put("person_gain", new TableInfo.Column("person_gain", "REAL", false, 0));
        _columnsPersonsTable.put("person_car", new TableInfo.Column("person_car", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPersonsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPersonsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPersonsTable = new TableInfo("persons_table", _columnsPersonsTable, _foreignKeysPersonsTable, _indicesPersonsTable);
        final TableInfo _existingPersonsTable = TableInfo.read(_db, "persons_table");
        if (! _infoPersonsTable.equals(_existingPersonsTable)) {
          throw new IllegalStateException("Migration didn't properly handle persons_table(com.serch.cars_implements.database_persons.Person).\n"
                  + " Expected:\n" + _infoPersonsTable + "\n"
                  + " Found:\n" + _existingPersonsTable);
        }
      }
    }, "29f82c41f7b1cdcce8831a5798a5f3c3");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "persons_table");
  }

  @Override
  public Dao_person dao_person() {
    if (_daoPerson != null) {
      return _daoPerson;
    } else {
      synchronized(this) {
        if(_daoPerson == null) {
          _daoPerson = new Dao_person_Impl(this);
        }
        return _daoPerson;
      }
    }
  }
}
