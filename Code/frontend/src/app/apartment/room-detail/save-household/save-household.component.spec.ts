import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveHouseholdComponent } from './save-household.component';

describe('SaveHouseholdComponent', () => {
  let component: SaveHouseholdComponent;
  let fixture: ComponentFixture<SaveHouseholdComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SaveHouseholdComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveHouseholdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
