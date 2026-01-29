import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { UsersModule } from './users/users.module';
import { AssociationsModule } from './associations/associations.module';
import { TypeOrmModule } from '@nestjs/typeorm/dist/typeorm.module';
import { Associations } from './associations/associations.entity';
import { User } from './users/user.entity';
import { UsersController } from './users/users.controller';
import { AuthModule } from './auth/auth.module';
import { Minute } from './minutes/minute.entity';
import { Role } from './roles/role.entity';
import { RolesModule } from './roles/roles.module';
import { MinutesModule } from './minutes/minutes.module';

@Module({
  imports: [
    RolesModule,
    MinutesModule,
    TypeOrmModule.forRoot({
      type: 'sqlite',
      database: 'mydatabase.db',
      entities: [User, Associations, Minute, Role],
      synchronize: true,
    }), UsersModule, AssociationsModule, AuthModule
  ],
  controllers: [AppController, UsersController],
  providers: [AppService],
})
export class AppModule { }
