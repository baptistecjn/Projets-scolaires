import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';

import { Associations } from './associations.entity';
import { AssociationsService } from './associations.service';
import { AssociationsController } from './associations.controller';

import { Role } from '../roles/role.entity';
import { User } from '../users/user.entity';
import { UsersModule } from '../users/users.module';

@Module({
  imports: [
    TypeOrmModule.forFeature([Associations, Role, User]),
    UsersModule,
  ],
  controllers: [AssociationsController],
  providers: [AssociationsService],
  exports: [AssociationsService],
})
export class AssociationsModule {}
