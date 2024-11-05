package com.example.hotdealnotifier.support;

import com.example.hotdealnotifier.support.db.DatabaseCleanerExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(DatabaseCleanerExtension.class)
public abstract class RepositoryTest {

}
