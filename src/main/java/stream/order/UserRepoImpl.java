package stream.order;

import java.util.List;

public class UserRepoImpl implements UserRepo {

	@Override
	public List<User> findAll() {
		List<User> list = List.of(new User("Kent Beck"),new User("Martin Fowler"));
		return list;
	}

}
