import { Controller, Get, Post, Put, Delete, Param, Body, ParseIntPipe, Query, HttpStatus, HttpException } from '@nestjs/common';
import { AssociationsService } from './associations.service';
import { User } from 'src/users/user.entity';
import { ApiTags } from '@nestjs/swagger';
import { AssociationsDTO } from './association.dto';

@ApiTags('associations')
@Controller('associations')
export class AssociationsController {
  constructor(private readonly associationsService: AssociationsService) { }

  // GET all associations
  @Get()
  async getAll(): Promise<AssociationsDTO[]> {
    return this.associationsService.getAll();
  }

  // GET association by ID
  @Get(':id')
  async getById(@Param('id', ParseIntPipe) id: number): Promise<AssociationsDTO> {
    return this.associationsService.getById(id);
  }

  // CREATE association
  @Post()
  async create(@Body('name') name: string): Promise<AssociationsDTO> {
    return this.associationsService.create(name);
  }

  // UPDATE association name
  @Put(':id')
  async update(
    @Param('id', ParseIntPipe) id: number,
    @Body('name') name: string,
  ): Promise<AssociationsDTO> {
    return this.associationsService.update(id, name);
  }

  // DELETE association
  @Delete(':id')
  async delete(@Param('id', ParseIntPipe) id: number): Promise<void> {
    return this.associationsService.deleteById(id);
  }

  // GET members of an association
  @Get(':id/members')
  async getMembers(@Param('id', ParseIntPipe) id: number): Promise<User[]> {
    return this.associationsService.getMembers(id);
  }

  // ADD a member to an association via Role
  @Post(':id/members')
  async addMember(
    @Param('id', ParseIntPipe) associationId: number,
    @Body() body: { userId?: number; idUser?: number; role?: string; roleName?: string },
  ) {
    const finalUserId = body.idUser || body.userId;
    const finalRole = body.role || body.roleName || 'Membre';

    if (!finalUserId) {
      throw new HttpException('idUser (ou userId) est obligatoire', HttpStatus.BAD_REQUEST);
    }

    await this.associationsService.addMember(associationId, finalUserId, finalRole);
    return true;
  }

  @Get(':id/minutes')
  async getAssociationMinutes(
    @Param('id') id: number,
    @Query('sort') sort: string = 'date',
    @Query('order') order: 'ASC' | 'DESC' = 'ASC',
  ) {
    return this.associationsService.getMinutes(id, sort, order);
  }

  // Supprimer un membre d'une association
  @Delete(':id/members/:userId')
  async removeMember(
      @Param('id', ParseIntPipe) id: number,
      @Param('userId', ParseIntPipe) userId: number
  ): Promise<void> {
      return this.associationsService.removeMember(id, userId);
  }

}
