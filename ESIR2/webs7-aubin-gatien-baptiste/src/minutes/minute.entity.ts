import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  ManyToMany,
  JoinTable,
} from "typeorm";
import { Associations } from "../associations/associations.entity";
import { User } from "../users/user.entity";

@Entity()
export class Minute {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  content: string;

  @Column()
  date: string;

  @ManyToOne(() => Associations, associations => associations.minutes, { 
    eager: true, 
    onDelete: 'CASCADE' 
  })
  association: Associations;

  @ManyToMany(() => User, (user) => user.minutesVoted, { eager: true })
  @JoinTable()
  voters: User[];
}