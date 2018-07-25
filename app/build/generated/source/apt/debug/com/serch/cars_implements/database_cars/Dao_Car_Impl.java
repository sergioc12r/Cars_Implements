package com.serch.cars_implements.database_cars;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class Dao_Car_Impl implements Dao_Car {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCars;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfCars;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCarByPlaca;

  private final SharedSQLiteStatement __preparedStmtOfUpdateCarById;

  private final SharedSQLiteStatement __preparedStmtOfUpdateColorsCars;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCars;

  public Dao_Car_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCars = new EntityInsertionAdapter<Cars>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `cars_table`(`id`,`car_marca`,`car_placa`,`car_modelo`,`car_puertas`,`car_tipo`,`car_color_puertas`,`car_color_llantas`,`car_color_capo`,`car_precio`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Cars value) {
        stmt.bindLong(1, value.getId());
        if (value.getMarca() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getMarca());
        }
        if (value.getPlaca() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPlaca());
        }
        if (value.getModelo() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getModelo());
        }
        if (value.getPuertas() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindLong(5, value.getPuertas());
        }
        if (value.getTipovehiculo() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getTipovehiculo());
        }
        if (value.getColorpuertas() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getColorpuertas());
        }
        if (value.getColorllantas() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getColorllantas());
        }
        if (value.getColorcapo() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getColorcapo());
        }
        if (value.getPrecio() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindLong(10, value.getPrecio());
        }
      }
    };
    this.__deletionAdapterOfCars = new EntityDeletionOrUpdateAdapter<Cars>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `cars_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Cars value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__preparedStmtOfDeleteCarByPlaca = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM cars_table WHERE car_placa= ? ";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateCarById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE cars_table SET car_placa= ?, car_modelo = ?, car_marca = ?, car_tipo= ?, car_puertas= ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateColorsCars = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE cars_table SET car_color_capo = ?, car_color_llantas = ?, car_color_puertas=? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllCars = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM cars_table";
        return _query;
      }
    };
  }

  @Override
  public void addCar(Cars car) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCars.insert(car);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteCar(Cars car) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfCars.handle(car);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeleteCarByPlaca(String placa) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCarByPlaca.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (placa == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, placa);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteCarByPlaca.release(_stmt);
    }
  }

  @Override
  public void UpdateCarById(Integer id, String s_placa, String s_marca, String s_modelo,
      String s_tipo, Integer s_puertas) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateCarById.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (s_placa == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, s_placa);
      }
      _argIndex = 2;
      if (s_modelo == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, s_modelo);
      }
      _argIndex = 3;
      if (s_marca == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, s_marca);
      }
      _argIndex = 4;
      if (s_tipo == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, s_tipo);
      }
      _argIndex = 5;
      if (s_puertas == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, s_puertas);
      }
      _argIndex = 6;
      if (id == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, id);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateCarById.release(_stmt);
    }
  }

  @Override
  public int updateColorsCars(Integer id, String colorPuertas, String colorLLantas,
      String colordemas) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateColorsCars.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (colordemas == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, colordemas);
      }
      _argIndex = 2;
      if (colorLLantas == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, colorLLantas);
      }
      _argIndex = 3;
      if (colorPuertas == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, colorPuertas);
      }
      _argIndex = 4;
      if (id == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindLong(_argIndex, id);
      }
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateColorsCars.release(_stmt);
    }
  }

  @Override
  public void deleteAllCars() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCars.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllCars.release(_stmt);
    }
  }

  @Override
  public List<Cars> getCars() {
    final String _sql = "select * from cars_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfMarca = _cursor.getColumnIndexOrThrow("car_marca");
      final int _cursorIndexOfPlaca = _cursor.getColumnIndexOrThrow("car_placa");
      final int _cursorIndexOfModelo = _cursor.getColumnIndexOrThrow("car_modelo");
      final int _cursorIndexOfPuertas = _cursor.getColumnIndexOrThrow("car_puertas");
      final int _cursorIndexOfTipovehiculo = _cursor.getColumnIndexOrThrow("car_tipo");
      final int _cursorIndexOfColorpuertas = _cursor.getColumnIndexOrThrow("car_color_puertas");
      final int _cursorIndexOfColorllantas = _cursor.getColumnIndexOrThrow("car_color_llantas");
      final int _cursorIndexOfColorcapo = _cursor.getColumnIndexOrThrow("car_color_capo");
      final int _cursorIndexOfPrecio = _cursor.getColumnIndexOrThrow("car_precio");
      final List<Cars> _result = new ArrayList<Cars>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Cars _item;
        _item = new Cars();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpMarca;
        _tmpMarca = _cursor.getString(_cursorIndexOfMarca);
        _item.setMarca(_tmpMarca);
        final String _tmpPlaca;
        _tmpPlaca = _cursor.getString(_cursorIndexOfPlaca);
        _item.setPlaca(_tmpPlaca);
        final String _tmpModelo;
        _tmpModelo = _cursor.getString(_cursorIndexOfModelo);
        _item.setModelo(_tmpModelo);
        final Integer _tmpPuertas;
        if (_cursor.isNull(_cursorIndexOfPuertas)) {
          _tmpPuertas = null;
        } else {
          _tmpPuertas = _cursor.getInt(_cursorIndexOfPuertas);
        }
        _item.setPuertas(_tmpPuertas);
        final String _tmpTipovehiculo;
        _tmpTipovehiculo = _cursor.getString(_cursorIndexOfTipovehiculo);
        _item.setTipovehiculo(_tmpTipovehiculo);
        final String _tmpColorpuertas;
        _tmpColorpuertas = _cursor.getString(_cursorIndexOfColorpuertas);
        _item.setColorpuertas(_tmpColorpuertas);
        final String _tmpColorllantas;
        _tmpColorllantas = _cursor.getString(_cursorIndexOfColorllantas);
        _item.setColorllantas(_tmpColorllantas);
        final String _tmpColorcapo;
        _tmpColorcapo = _cursor.getString(_cursorIndexOfColorcapo);
        _item.setColorcapo(_tmpColorcapo);
        final Long _tmpPrecio;
        if (_cursor.isNull(_cursorIndexOfPrecio)) {
          _tmpPrecio = null;
        } else {
          _tmpPrecio = _cursor.getLong(_cursorIndexOfPrecio);
        }
        _item.setPrecio(_tmpPrecio);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
