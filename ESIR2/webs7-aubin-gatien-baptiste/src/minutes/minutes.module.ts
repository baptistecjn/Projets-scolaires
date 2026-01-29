import { Module } from "@nestjs/common";
import { TypeOrmModule } from "@nestjs/typeorm";
import { Minute } from "./minute.entity";
import { MinutesService } from "./minutes.service";
import { MinutesController } from "./minutes.controller";
import { User } from "../users/user.entity";
import { Associations } from "../associations/associations.entity";

@Module({
  imports: [TypeOrmModule.forFeature([Minute, User, Associations])],
  controllers: [MinutesController],
  providers: [MinutesService],
  exports: [MinutesService],
})
export class MinutesModule {}
