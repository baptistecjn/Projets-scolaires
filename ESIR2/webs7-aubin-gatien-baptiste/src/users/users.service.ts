import { Body, Injectable, NotFoundException, Param } from '@nestjs/common';
import { User } from './user.entity'

import { InjectRepository } from '@nestjs/typeorm';
import { Equal, Repository } from 'typeorm';
import * as bcrypt from 'bcrypt';

@Injectable()
export class UsersService {

  constructor(
    @InjectRepository(User)
    private repository: Repository<User>) { }

  // GET ALL
  public async getAll(): Promise<User[]> {
    return this.repository.find();
  }

  // GET BY ID
  public async getById(id: number): Promise<User> {
    const user = await this.repository.findOne({ where: { id: Equal(id) },
      relations: ['roles', 'roles.association']
    });
    if (!user) {
      throw new NotFoundException(`User with id ${id} not found`);
    }
    return user;
  }


  // CREATE
  public async create(username: string, lastname: string, firstname: string, age: number, password: string): Promise<User> {
    const hash = await bcrypt.hash(password, 10);
    const newUser = this.repository.create({ username: username, lastname: lastname, firstname: firstname, age: age, password: hash });
    return this.repository.save(newUser);
  }


  // UPDATE
  public async update(id: number, lastname?: string, firstname?: string, age?: number, password?: string): Promise<User> {
    const user = await this.getById(id); // utilise getById pour lever l'erreur si non trouvé

    if (lastname !== undefined) user.lastname = lastname;
    if (firstname !== undefined) user.firstname = firstname;
    if (age !== undefined) user.age = age;
    if (password !== undefined) user.password = password;

    return await this.repository.save(user);
  }


  // DELETE
  public async delById(id: number): Promise<boolean> {
    const result = await this.repository.delete(id);
    if (result.affected === 0) {
      throw new NotFoundException(`User with id ${id} not found`);
    }
    return true;
  }

  public async findOne(username: string): Promise<User | null> {
    return this.repository.findOne({ where: { username: username } });
  }

}
