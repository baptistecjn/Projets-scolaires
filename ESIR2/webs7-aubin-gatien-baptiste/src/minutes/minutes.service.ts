import { Injectable, NotFoundException } from "@nestjs/common";
import { InjectRepository } from "@nestjs/typeorm";
import { Repository, In } from "typeorm";
import { Minute } from "./minute.entity";
import { MinuteInput } from "./minute.input";
import { MinuteUpdate } from "./minute.update";
import { Associations } from "../associations/associations.entity";
import { User } from "../users/user.entity";

@Injectable()
export class MinutesService {
  constructor(
    @InjectRepository(Minute)
    private minuteRepo: Repository<Minute>,

    @InjectRepository(User)
    private userRepo: Repository<User>,

    @InjectRepository(Associations)
    private associationRepo: Repository<Associations>,
  ) {}

  async create(input: MinuteInput): Promise<Minute> {
    const association = await this.associationRepo.findOne({
      where: { id: input.idAssociation },
    });

    if (!association) throw new NotFoundException("Association not found");

    const voters = await this.userRepo.find({
      where: { id: In(input.idVoters) },
    });

    const minute = this.minuteRepo.create({
      content: input.content,
      date: input.date,
      association,
      voters,
    });

    return this.minuteRepo.save(minute);
  }

  findAll() {
    return this.minuteRepo.find();
  }

  async findOne(id: number) {
    const m = await this.minuteRepo.findOne({ where: { id } });
    if (!m) throw new NotFoundException("Minute not found");
    return m;
  }

  async update(id: number, input: MinuteUpdate) {
    const minute = await this.findOne(id);

    const association = await this.associationRepo.findOne({
      where: { id: input.idAssociation },
    });

    const voters = await this.userRepo.find({
      where: { id: In(input.idVoters) },
    });

    minute.content = input.content;
    minute.date = input.date;
    minute.association = association!;
    minute.voters = voters;

    return this.minuteRepo.save(minute);
  }

  async remove(id: number) {
    const minute = await this.findOne(id);
    return this.minuteRepo.remove(minute);
  }
}
