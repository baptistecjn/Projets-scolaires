import { Controller, Get, Post, Body, Param, Put, Delete } from "@nestjs/common";
import { MinutesService } from "./minutes.service";
import { MinuteInput } from "./minute.input";
import { MinuteUpdate } from "./minute.update";

@Controller("minutes")
export class MinutesController {
  constructor(private service: MinutesService) {}

  @Post()
  create(@Body() input: MinuteInput) {
    return this.service.create(input);
  }

  @Get()
  findAll() {
    return this.service.findAll();
  }

  @Get(":id")
  findOne(@Param("id") id: number) {
    return this.service.findOne(id);
  }

  @Put(":id")
  update(@Param("id") id: number, @Body() update: MinuteUpdate) {
    return this.service.update(id, update);
  }

  @Delete(":id")
  remove(@Param("id") id: number) {
    return this.service.remove(id);
  }
}
