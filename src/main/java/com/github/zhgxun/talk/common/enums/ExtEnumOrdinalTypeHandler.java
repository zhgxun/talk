package com.github.zhgxun.talk.common.enums;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 扩展的枚举处理类, 详情可参考mybatis实现类
 *
 * @param <E> 泛型枚举类
 * @see org.apache.ibatis.type.EnumOrdinalTypeHandler
 */
public class ExtEnumOrdinalTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    public interface ValuedEnum {
        int getValue();
    }

    private Class<E> type;
    private Map<Integer, E> map = new HashMap<>();

    public ExtEnumOrdinalTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        E[] enums = type.getEnumConstants();
        if (enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
        for (E e : enums) {
            ValuedEnum valuedEnum = (ValuedEnum) e;
            map.put(valuedEnum.getValue(), e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E o, JdbcType jdbcType) throws SQLException {
        ValuedEnum valuedEnum = (ValuedEnum) o;
        ps.setInt(i, valuedEnum.getValue());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return getValuedEnum(i);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return getValuedEnum(i);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return getValuedEnum(i);
        }
    }

    private E getValuedEnum(int value) {
        try {
            return map.get(value);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + value + " to " + type.getSimpleName() + " by value.", ex);
        }
    }
}
