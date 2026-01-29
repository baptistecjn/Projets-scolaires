import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Role } from './role.entity';
import { RoleInput } from './role.input';
import { RoleUpdate } from './role.update';
import { User } from 'src/users/user.entity';

@Injectable()
export class RolesService {
  async findUsersByRoleName(roleName: string): Promise<User[]> {
    const role = await this.roleRepository.findOne({
      where: { name: roleName },
      relations: ['user'],  
    });

    if (!role) throw new NotFoundException('Role not found');

    return [role.user];
  }


  constructor(
    @InjectRepository(Role)
    private roleRepository: Repository<Role>,
  ) {}

  async create(input: RoleInput): Promise<Role> {
    const role = this.roleRepository.create(input);
    return this.roleRepository.save(role);
  }

  async findOne(idUser: number, idAssociation: number): Promise<Role> {
    const role = await this.roleRepository.findOne({
      where: { idUser, idAssociation },
    });

    if (!role) throw new NotFoundException('Role not found');
    return role;
  }

  async findAll(): Promise<Role[]> {
    return this.roleRepository.find();
  }

  async update(idUser: number, idAssociation: number, update: RoleUpdate): Promise<Role> {
    const role = await this.findOne(idUser, idAssociation);
    role.name = update.name;
    return this.roleRepository.save(role);
  }

  async delete(idUser: number, idAssociation: number): Promise<void> {
    const result = await this.roleRepository.delete({ idUser, idAssociation });

    if (result.affected === 0) {
        throw new NotFoundException('Role not found');
    }
  }
}
