package kyh.tam.sql;

public interface TransactionCallback {
  Object doInTransaction() throws Exception;
}
