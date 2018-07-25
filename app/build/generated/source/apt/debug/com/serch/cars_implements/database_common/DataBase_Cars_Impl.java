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
import com.serch.cars_implements.database_cars.Dao_Car;
import com.serch.cars_implements.database_cars.Dao_Car_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.util.HashMap;
import java.util.HashSet;

public class DataBase_Cars_Impl extends DataBase_Cars {
  private volatile Dao_Car _daoCar;

  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `cars_table` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `car_marca` TEXT, `car_placa` TEXT, `car_modelo` TEXT, `car_puertas` INTEGER, `car_tipo` TEXT, `car_color_puertas` TEXT, `car_color_llantas` TEXT, `car_color_capo` TEXT, `car_precio` INTEGER)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"80691ed6c1b65e81c15ea3f2db398f82\")");
      }

      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `cars_table`");
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
        final HashMap<String, TableInfo.Column> _columnsCarsTable = new HashMap<String, TableInfo.Column>(10);
        _columnsCarsTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsCarsTable.put("car_marca", new TableInfo.Column("car_marca", "TEXT", false, 0));
        _columnsCarsTable.put("car_placa", new TableInfo.Column("car_placa", "TEXT", false, 0));
        _columnsCarsTable.put("car_modelo", new TableInfo.Column("car_modelo", "TEXT", false, 0));
        _columnsCarsTable.put("car_puertas", new TableInfo.Column("car_puertas", "INTEGER", false, 0));
        _columnsCarsTable.put("car_tipo", new TableInfo.Column("car_tipo", "TEXT", false, 0));
        _columnsCarsTable.put("car_color_puertas", new TableInfo.Column("car_color_puertas", "TEXT", false, 0));
        _columnsCarsTable.put("car_color_llantas", new TableInfo.Column("car_color_llantas", "TEXT", false, 0));
        _columnsCarsTable.put("car_color_capo", new TableInfo.Column("car_color_capo", "TEXT", false, 0));
        _columnsCarsTable.put("car_precio", new TableInfo.Column("car_precio", "INTEGER", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCarsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCarsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCarsTable = new TableInfo("cars_table", _columnsCarsTable, _foreignKeysCarsTable, _indicesCarsTable);
        final TableInfo _existingCarsTable = TableInfo.read(_db, "cars_table");
        if (! _infoCarsTable.equals(_existingCarsTable)) {
          throw new IllegalStateException("Migration didn't properly handle cars_table(com.serch.cars_implements.database_cars.Cars).\n"
                  + " Expected:\n" + _infoCarsTable + "\n"
                  + " Found:\n" + _existingCarsTable);
        }
      }
    }, "80691ed6c1b65e81c15ea3f2db398f82");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "cars_table");
  }

  @Override
  public Dao_Car dao_car() {
    if (_daoCar != null) {
      return _daoCar;
    } else {
      synchronized(this) {
        if(_daoCar == null) {
          _daoCar = new Dao_Car_Impl(this);
        }
        return _daoCar;
      }
    }
  }
}
