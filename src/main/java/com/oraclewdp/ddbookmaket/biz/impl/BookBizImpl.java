package com.oraclewdp.ddbookmaket.biz.impl;

import java.util.List;

import com.oraclewdp.ddbookmaket.biz.BookBiz;
import com.oraclewdp.ddbookmaket.dao.BookDao;
import com.oraclewdp.ddbookmaket.dao.impl.BookDaoJdbcImpl;
import com.oraclewdp.ddbookmaket.model.Book;

public class BookBizImpl implements BookBiz {

	@Override
	public boolean save(Book bookType) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.save(bookType);
	}

	@Override
	public List<Book> findAll(int currentPage,String name,int sid) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.findAll(currentPage,name,sid);
	}

	@Override
	public int tatalRow(String name,int sid) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.total(name,sid);
	}

	@Override
	public int delById(int id) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.del(id);
	}

	@Override
	public Book findBookById(int id) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.find(id);
	}

	@Override
	public boolean update(Book book) {
		BookDao bookDao=new BookDaoJdbcImpl();
		return bookDao.update(book);
	}
}
