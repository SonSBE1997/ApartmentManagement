import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveRepairComponent } from './save-repair.component';

describe('SaveRepairComponent', () => {
  let component: SaveRepairComponent;
  let fixture: ComponentFixture<SaveRepairComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaveRepairComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveRepairComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
