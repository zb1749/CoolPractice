package dbutil.queryconfig.jsqlparser.statement;

import dbutil.queryconfig.jsqlparser.statement.create.table.CreateTable;
import dbutil.queryconfig.jsqlparser.statement.delete.Delete;
import dbutil.queryconfig.jsqlparser.statement.drop.Drop;
import dbutil.queryconfig.jsqlparser.statement.insert.Insert;
import dbutil.queryconfig.jsqlparser.statement.replace.Replace;
import dbutil.queryconfig.jsqlparser.statement.select.Select;
import dbutil.queryconfig.jsqlparser.statement.truncate.Truncate;
import dbutil.queryconfig.jsqlparser.statement.update.Update;

public interface StatementVisitor {

    public void visit(Select select);

    public void visit(Delete delete);

    public void visit(Update update);

    public void visit(Insert insert);

    public void visit(Replace replace);

    public void visit(Drop drop);

    public void visit(Truncate truncate);

    public void visit(CreateTable createTable);
}
