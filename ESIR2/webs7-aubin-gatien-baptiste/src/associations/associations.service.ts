import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Associations } from './associations.entity';
import { Role } from '../roles/role.entity';
import { UsersService } from 'src/users/users.service';
import { AssociationsDTO } from './association.dto';
import { Member } from './association.member';
import { Minute } from 'src/minutes/minute.entity';

@Injectable()
export class AssociationsService {
  constructor(
    @InjectRepository(Associations)
    private readonly repository: Repository<Associations>,
    @InjectRepository(Role)
    private readonly roleRepository: Repository<Role>,
    private readonly usersService: UsersService,
  ) { }

  private toDTO(association: Associations): AssociationsDTO {
    const dto = new AssociationsDTO();
    dto.id = association.id;
    dto.name = association.name;

    dto.members = (association.roles || []).map(role => {
      const member = new Member();
      member.userId = role.user.id;
      member.lastname = role.user.lastname;
      member.firstname = role.user.firstname;
      member.age = role.user.age;
      member.role = role.name;
      return member;
    });

    return dto;
  }

  // GET ALL Associations
  async getAll(): Promise<AssociationsDTO[]> {
    const associations = await this.repository.find({
      relations: ['roles', 'roles.user'],
    });
    return associations.map(assoc => this.toDTO(assoc));
  }

  // GET BY ID
  async getById(id: number): Promise<AssociationsDTO> {
    const association = await this.repository.findOne({
      where: { id: id },
      relations: ['roles', 'roles.user'],
    });

    if (!association) {
      throw new NotFoundException(`Association avec l'id ${id} introuvable`);
    }
    return this.toDTO(association);
  }

  // CREATE Association
  async create(name: string): Promise<AssociationsDTO> {
    const newAssociation = this.repository.create({ name });
    const savedAssociation = await this.repository.save(newAssociation);
    return this.toDTO(savedAssociation);
  }

  // UPDATE Association name
  async update(id: number, name: string): Promise<AssociationsDTO> {
    const association = await this.repository.findOne({
      where: { id: id },
      relations: ['roles', 'roles.user']
    });

    if (!association) {
      throw new NotFoundException(`Association avec l'id ${id} introuvable`);
    }
    association.name = name;
    const updatedAssociation = await this.repository.save(association);

    return this.toDTO(updatedAssociation);
  }

  // DELETE Association
  async deleteById(id: number): Promise<void> {
    const result = await this.repository.delete(id);
    if (result.affected === 0) {
      throw new NotFoundException(`Association avec l'id ${id} introuvable`);
    }
  }

  // GET USERS of an association via Roles
  async getMembers(id: number): Promise<any[]> {
    const roles = await this.roleRepository.find({
      where: { idAssociation: id },
      relations: ['user'],
    });
    return roles.map(role => {
      return {
        ...role.user,

        role: role.name
      };
    });
  }

  // ADD a user to an association via Role
  async addMember(associationId: number, userId: number, roleName: string): Promise<Role> {
    const association = await this.repository.findOne({ where: { id: associationId } });

    if (!association) {
      throw new NotFoundException(`Association avec l'id ${associationId} introuvable`);
    }

    const user = await this.usersService.getById(userId);

    const role = this.roleRepository.create({
      idUser: user.id,
      idAssociation: association.id,
      name: roleName,
      user,
      association,
    });
    return this.roleRepository.save(role);
  }


  async getMinutes(associationId: number, sort: string, order: 'ASC' | 'DESC'): Promise<Minute[]> {
    const association = await this.repository.findOne({
      where: { id: associationId },
      relations: ['minutes'],
    });

    if (!association) throw new NotFoundException('Association not found');

    return association.minutes.sort((a, b) => {
      if (order === 'ASC') return new Date(a[sort]).getTime() - new Date(b[sort]).getTime();
      else return new Date(b[sort]).getTime() - new Date(a[sort]).getTime();
    });
  }

  async removeMember(associationId: number, userId: number): Promise<void> {
    const role = await this.roleRepository.findOne({
      where: {
        idAssociation: associationId,
        idUser: userId
      }
    });

    if (!role) {
      throw new NotFoundException("Membre introuvable dans cette association");
    }

    await this.roleRepository.delete({
      idAssociation: associationId,
      idUser: userId
    });
  }

}
