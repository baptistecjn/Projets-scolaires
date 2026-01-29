import { Controller, Get, Param, Body, Post, Put, Delete } from '@nestjs/common';
import { User } from './user.entity';
import { UsersService } from './users.service';
import { ApiTags } from '@nestjs/swagger';
import { UserInput } from './UserInput';

@ApiTags('users')
@Controller('users')
export class UsersController {
    
  constructor(private service: UsersService) { }
  
  @Get()
  async getAll(): Promise<User[]> {
    return await this.service.getAll();
  }

  @Get(':id')
  async getById(@Param('id') id: string): Promise<User> {
    return await this.service.getById(Number(id));
  }

  @Post()
  public async create(@Body() input: UserInput): Promise<User> {
    return await this.service.create(
        input.username, 
        input.lastname, 
        input.firstname, 
        input.age, 
        input.password
    );
  }
  
  @Put(':id')
  async update(
    @Param('id') id: string,
    @Body() input: { lastname?: string; firstname?: string; age?: number; password?: string}
  ): Promise<User> {
    return await this.service.update(
      Number(id),
      input.lastname,
      input.firstname,
      input.age,
      input.password
    );
  }

  @Delete(':id')
  async delById(@Param('id') id: string): Promise<boolean> {
    return await this.service.delById(Number(id));
  }

  @Get(':id/roles')
async getUserRoles(@Param('id') id: string) { 
  const user = await this.service.getById(Number(id)); 
  const roles = user.roles || [];
  
  console.log(`Envoi de ${roles.length} rôles pour l'user ${id}`);
  return roles;
  }
}