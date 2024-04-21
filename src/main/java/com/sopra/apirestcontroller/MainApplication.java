package com.sopra.apirestcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	/*@Bean
	CommandLineRunner init(UserRepositoryImpl userRepository) {
		return args -> {
			// Create PERMISSIONS
			PermissionEntity createPermission = new PermissionEntity("CREATE");

			PermissionEntity readPermission = new PermissionEntity("READ");

			PermissionEntity updatePermission = new PermissionEntity("UPDATE");

			PermissionEntity deletePermission = new PermissionEntity("DELETE");

			// Create ROLES 
			UserRoleEntity roleAdmin = new UserRoleEntity(RoleUserEnum.ADMIN,
					Set.of(createPermission, readPermission, updatePermission, deletePermission));

			UserRoleEntity roleUser = new UserRoleEntity(RoleUserEnum.USER, Set.of(readPermission));

			//CREATE USERS 
			UserEntity userJMaldonado = new UserEntity("jmaldonado",
					"$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6", true, true, true, true,
					Set.of(roleUser));

			UserEntity userTesifon = new UserEntity("tesifon",
					"$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6", true, true, true, true,
					Set.of(roleAdmin));

			UserDtoMapperImpl oDtoMapperImpl = new UserDtoMapperImpl();
			UserRepositoryImpl oUserRepositoryImpl = new UserRepositoryImpl();
		};
	}*/
}
